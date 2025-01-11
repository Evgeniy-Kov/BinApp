package com.example.binapp.data

import com.example.binapp.data.network.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response

    companion object {
        const val NO_CONNECTION = "-1"
        const val SUCCESS = "200"
        const val BAD_REQUEST = "400"
    }
}