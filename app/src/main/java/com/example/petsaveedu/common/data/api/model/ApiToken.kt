package com.example.petsaveedu.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant


@JsonClass(generateAdapter = true)
data class ApiToken(
    @field:Json(name = "token_type") val tokenType: String?,
    @field:Json(name = "expires_in") val expiresInSeconds: Int?,
    @field:Json(name = "access_token") val accessToken: String?
) {

    fun isValid(): Boolean {
        return (
                tokenType.isNullOrEmpty()
                        || expiresInSeconds == null || expiresInSeconds <= 0 ||
                        accessToken.isNullOrEmpty()
                ).not()
    }

    val expiresAt: Long
        get() {
            if(expiresInSeconds == null) return 0L

            return Instant.now().plusSeconds(expiresInSeconds.toLong()).epochSecond
        }


    companion object {
        val INVALID = ApiToken("", -1, "")
    }
}
