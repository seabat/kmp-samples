package dev.seabat.kmp.withsubmodule

import android.app.Application
import dev.seabat.kmp.withsubmodule.shared.di.initAndroidKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAndroidKoin {
            androidContext(this@MainApplication)
        }
    }
}