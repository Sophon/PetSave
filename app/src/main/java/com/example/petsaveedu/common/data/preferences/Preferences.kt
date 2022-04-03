package com.example.petsaveedu.common.data.preferences

import com.example.petsaveedu.common.data.api.model.ApiToken

interface Preferences {

    fun getToken(): String
    fun getTokenExpirationTime(): Long

    fun saveToken(token: String)
    fun saveTokenExpirationTime(time: Long)
    fun saveTokenType(type: String)

    fun deleteToken()
}