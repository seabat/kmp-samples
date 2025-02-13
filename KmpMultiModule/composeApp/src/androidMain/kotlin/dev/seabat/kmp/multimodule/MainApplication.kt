package dev.seabat.kmp.multimodule

import android.app.Application
import dev.seabat.kmp.multimodule.shared.di.initAndroidDataKoin
import dev.seabat.kmp.multimodule.shared.di.initAndroidDomainKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // FIXME: startKoin は１度しか呼べないので initAndroidDataKoin と initAndroidDomainKoin を
        //        まとめる必要がある。
        initAndroidDataKoin {
            androidContext(this@MainApplication)
        }
        initAndroidDomainKoin()
    }
}