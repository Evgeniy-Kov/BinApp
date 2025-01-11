package com.example.binapp.di

import com.example.binapp.data.NetworkClient
import com.example.binapp.data.network.BinApi
import com.example.binapp.data.network.RetrofitNetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<BinApi> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }
}