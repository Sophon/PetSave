package com.example.petsaveedu.common.data.api

object ApiConstants {

    const val BASE_ENDPOINT = "https://api.petfinder.com/v2/"
    const val AUTH_ENDPOINT = "oauth2/token/"

    const val ANIMALS_ENDPOINT = "animals"

    const val KEY = "GwWHyrovNzYR1OQAQCA8bGMSylmO38WxFK99DQfhUVwE1UVj8S"
    const val SECRET = "8gIYjmZy0zhalGwu8hwo1ZlqrGDKzHwuyvSWefLG"

}

object ApiParameters {
    const val AUTH_HEADER = "Authorization"
    const val TOKEN_TYPE = "Bearer"
    const val GRANT_TYPE_KEY = "grant_type"
    const val GRANT_TYPE_VALUE = "client_credentials"
    const val CLIENT_ID_KEY = "client_id"
    const val CLIENT_SECRET_KEY = "client_secret"
}