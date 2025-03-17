package dev.seabat.kmp.firebasestorage

import android.app.Application
import dev.seabat.kmp.firebasestorage.di.initAndroidKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAndroidKoin {
            androidContext(this@MainApplication)
        }
    }
}