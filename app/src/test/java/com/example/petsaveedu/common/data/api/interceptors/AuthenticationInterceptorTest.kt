package com.example.petsaveedu.common.data.api.interceptors

import com.google.common.truth.Truth.*
import android.os.Build
import com.example.petsaveedu.common.data.api.ApiConstants
import com.example.petsaveedu.common.data.api.ApiParameters
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
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
    fun authInterceptor_validToken() {
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
}