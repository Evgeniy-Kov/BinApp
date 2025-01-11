package com.example.binapp

import android.app.Application
import com.example.binapp.di.dataModule
import com.example.binapp.di.interactorModule
import com.example.binapp.di.repositoryModule
import com.example.binapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, repositoryModule, viewModelModule)
        }

    }
}