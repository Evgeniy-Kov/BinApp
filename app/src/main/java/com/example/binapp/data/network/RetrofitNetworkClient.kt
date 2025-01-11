package com.example.binapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.binapp.data.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val binApi: BinApi,
    private val context: Context
) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        return withContext(Dispatchers.IO) {
            try {
                if (!isInternetAvailable()) {
                    Response(NetworkClient.NO_CONNECTION)
                }
                if (dto is BinRequest) {
                    BinResponse(binApi.searchBinInfo(dto.expression)).apply {
                        resultCode = NetworkClient.SUCCESS
                    }
                } else {
                    Response(NetworkClient.BAD_REQUEST)
                }
            } catch (e: Exception) {
                Response(e.message.toString())
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
        return false
    }
}