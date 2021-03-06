package com.example.petsaveedu.common.data.api.interceptors

import com.example.petsaveedu.common.data.api.ApiConstants
import com.example.petsaveedu.common.data.api.ApiConstants.AUTH_ENDPOINT
import com.example.petsaveedu.common.data.api.ApiParameters.AUTH_HEADER
import com.example.petsaveedu.common.data.api.ApiParameters.CLIENT_ID_KEY
import com.example.petsaveedu.common.data.api.ApiParameters.CLIENT_SECRET_KEY
import com.example.petsaveedu.common.data.api.ApiParameters.GRANT_TYPE_KEY
import com.example.petsaveedu.common.data.api.ApiParameters.GRANT_TYPE_VALUE
import com.example.petsaveedu.common.data.api.ApiParameters.TOKEN_TYPE
import com.example.petsaveedu.common.data.api.model.ApiToken
import com.example.petsaveedu.common.data.preferences.Preferences
import com.squareup.moshi.Moshi
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.threeten.bp.Instant
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val preferences: Preferences
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getToken()
        val tokenExpirationTime = Instant.ofEpochSecond(preferences.getTokenExpirationTime())
        val request = chain.request()

        val interceptedRequest: Request = if(tokenExpirationTime.isAfter(Instant.now())) {
            chain.createAuthenticatedRequest(token)
        } else {
            //token expired, gotta refresh
            val tokenRefreshResponse = chain.refreshToken()

            if(tokenRefreshResponse.isSuccessful) {
                val newToken = mapToken(tokenRefreshResponse)

                if(newToken.isValid()) {
                    storeToken(newToken)
                    chain.createAuthenticatedRequest(newToken.accessToken!!)
                } else {
                    request
                }
            } else {
                request
            }
        }

        return chain.proceed(interceptedRequest)
    }


    private fun Interceptor.Chain.createAuthenticatedRequest(token: String): Request {
        return request()
            .newBuilder()
            .addHeader(AUTH_HEADER, TOKEN_TYPE + token)
            .build()
    }

    private fun Interceptor.Chain.refreshToken(): Response {
        val url = request()
            .url
            .newBuilder(AUTH_ENDPOINT)!!
            .build()

        val body = FormBody.Builder()
            .add(GRANT_TYPE_KEY, GRANT_TYPE_VALUE)
            .add(CLIENT_ID_KEY, ApiConstants.KEY)
            .add(CLIENT_SECRET_KEY, ApiConstants.SECRET)
            .build()

        val tokenRefreshRequest = request()
            .newBuilder()
            .post(body)
            .url(url)
            .build()

        return proceedDeletingTokenIfUnauthorized(tokenRefreshRequest)
    }

    private fun Interceptor.Chain.proceedDeletingTokenIfUnauthorized(
        request: Request
    ): Response {
        val response = proceed(request)

        if(response.code == UNAUTHORIZED) {
            preferences.deleteToken()
        }

        return response
    }


    private fun mapToken(tokenRefreshResponse: Response): ApiToken {
        val moshi = Moshi.Builder().build()
        val tokenAdapter = moshi.adapter(ApiToken::class.java)
        val responseBody = tokenRefreshResponse.body!! //response must be successful

        return tokenAdapter.fromJson(responseBody.string()) ?: ApiToken.INVALID
    }

    private fun storeToken(token: ApiToken) {
        preferences.apply {
            saveToken(token.accessToken!!)
            saveTokenExpirationTime(token.expiresAt)
            saveTokenType(token.tokenType!!)
        }
    }


    companion object {
        const val UNAUTHORIZED = 401
    }
}