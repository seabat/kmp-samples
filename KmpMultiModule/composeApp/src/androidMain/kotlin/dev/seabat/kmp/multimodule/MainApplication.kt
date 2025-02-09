package dev.seabat.kmp.multimodule

import android.app.Application
import dev.seabat.kmp.multimodule.shared.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}