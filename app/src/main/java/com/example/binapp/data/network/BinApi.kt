package com.example.binapp.data.network

import com.example.binapp.data.dto.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinApi {
    @Headers(
        "Accept-Version: 3"
    )
    @GET("/{bin_number}")
    suspend fun searchBinInfo(@Path("bin_number") binNumber: String): BinInfoDto

}