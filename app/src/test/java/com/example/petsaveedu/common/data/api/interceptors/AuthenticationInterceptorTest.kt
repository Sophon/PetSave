package com.example.petsaveedu.common.data.api.interceptors

import com.google.common.truth.Truth.*
import android.os.Build
import com.example.petsaveedu.common.data.api.ApiConstants
import com.example.petsaveedu.common.data.api.ApiParameters
import com.example.petsaveedu.common.data.api.utils.JsonReader.getJson
import com.example.petsaveedu.common.data.preferences.Preferences
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.threeten.bp.Instant


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.P])
class AuthenticationInterceptorTest {

    private lateinit var preferences: Preferences
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authenticationInterceptor: AuthenticationInterceptor
    private lateinit var okHttpClient: OkHttpClient

    private val endpointSeparator = "/"
    private val animalsEndpointPath = endpointSeparator + ApiConstants.ANIMALS_ENDPOINT
    private val authEndpointPath = endpointSeparator + ApiConstants.AUTH_ENDPOINT

    private val validToken = "validToken"
    private val expiredToken = "expiredToken"


    @Before
    fun setup() {
        preferences = mock(Preferences::class.java)

        mockWebServer = MockWebServer()
        mockWebServer.start(8080)

        authenticationInterceptor = AuthenticationInterceptor(preferences)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun validToken_requestHasCorrectHeader() {
        //Given
        `when`(preferences.getToken()).thenReturn(validToken)
        `when`(preferences.getTokenExpirationTime()).thenReturn(
            Instant.now().plusSeconds(3600).epochSecond
        )
        mockWebServer.dispatcher = getDispatcherForValidToken()

        //When
        okHttpClient.newCall(
            Request.Builder()
                .url(mockWebServer.url(ApiConstants.ANIMALS_ENDPOINT))
                .build()
        ).execute()

        //Then
        mockWebServer.takeRequest().apply {
            assertThat(method).isEqualTo("GET")
            assertThat(path).isEqualTo(animalsEndpointPath)
            assertThat(getHeader(ApiParameters.AUTH_HEADER))
                .isEqualTo(ApiParameters.TOKEN_TYPE + validToken)
        }
    }

    @Test
    fun expiredToken_authRequestHasCorrectHeader() {
        //Given
        givenExpiredToken()

        //When
        okHttpClient.newCall(
            Request.Builder()
                .url(mockWebServer.url(ApiConstants.ANIMALS_ENDPOINT))
                .build()
        ).execute()

        //Then
        mockWebServer.takeRequest().let { tokenRequest ->
            assertThat(tokenRequest.method).isEqualTo("POST")
            assertThat(tokenRequest.path).isEqualTo(authEndpointPath)
        }
    }
    
    @Test
    fun expiredToken_preferenceMethodsCalledInCorrectOrder() {
        //Given
        givenExpiredToken()

        //When
        okHttpClient.newCall(
            Request.Builder()
                .url(mockWebServer.url(ApiConstants.ANIMALS_ENDPOINT))
                .build()
        ).execute()

        //Then
        inOrder(preferences).let { order ->
            order.verify(preferences).getToken()
            order.verify(preferences).saveToken(validToken)
        }
    }

    @Test
    fun expiredToken_preferencesMethodsCalledCorrectAmount() {
        //Given
        givenExpiredToken()

        //When
        okHttpClient.newCall(
            Request.Builder()
                .url(mockWebServer.url(ApiConstants.ANIMALS_ENDPOINT))
                .build()
        ).execute()

        //Then
        verify(preferences, times(1)).getToken()
        verify(preferences, times(1)).saveToken(validToken)
        verify(preferences, times(1)).getTokenExpirationTime()
        verify(preferences, times(1)).saveTokenExpirationTime(anyLong())
        verify(preferences, times(1))
            .saveTokenType(ApiParameters.TOKEN_TYPE.trim())

        verifyNoMoreInteractions(preferences)
    }
    
    @Test
    fun expiredToken_animalRequestHasCorrectHeader() {
        //Given
        givenExpiredToken()
        
        //When
        okHttpClient.newCall(
            Request.Builder()
                .url(mockWebServer.url(ApiConstants.ANIMALS_ENDPOINT))
                .build()
        ).execute()


        //Then
        mockWebServer.takeRequest()
        mockWebServer.takeRequest().let { animalRequest ->
            assertThat(animalRequest.method).isEqualTo("GET")
            assertThat(animalRequest.path).isEqualTo(animalsEndpointPath)
            assertThat(animalRequest.getHeader(ApiParameters.AUTH_HEADER))
                .isEqualTo(ApiParameters.TOKEN_TYPE + validToken)
        }
    }


    private fun getDispatcherForValidToken(): Dispatcher {
        return object: Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when(request.path) {
                    animalsEndpointPath -> { MockResponse().setResponseCode(200) }
                    else -> { MockResponse().setResponseCode(404) }
                }
            }
        }
    }

    private fun getDispatcherForExpiredToken(): Dispatcher {
        return object: Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when(request.path) {
                    authEndpointPath -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(getJson("validToken.json"))
                    }
                    animalsEndpointPath -> { MockResponse().setResponseCode(200) }
                    else -> { MockResponse().setResponseCode(404) }
                }
            }
        }
    }

    private fun givenExpiredToken() {
        `when`(preferences.getToken()).thenReturn(expiredToken)
        `when`(preferences.getTokenExpirationTime()).thenReturn(
            Instant.now().minusMillis(3600).epochSecond
        )
        mockWebServer.dispatcher = getDispatcherForExpiredToken()
    }
}