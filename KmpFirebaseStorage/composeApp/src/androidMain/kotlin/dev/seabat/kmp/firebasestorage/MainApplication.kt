package dev.seabat.kmp.firebasestorage

import android.app.Application
import dev.seabat.kmp.firebasestorage.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}