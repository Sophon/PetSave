package com.example.petsaveedu.common.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun isConnected(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return manager.getNetworkCapabilities(manager.activeNetwork)?.hasCapability(NET_CAPABILITY_INTERNET) == true
    }
}