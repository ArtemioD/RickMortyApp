package org.example.project

import android.app.Application
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@RickMortyApp)
        }
    }
}