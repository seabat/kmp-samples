package dev.seabat.kmp.firebasestorage

import android.app.Application
import dev.seabat.kmp.firebasestorage.di.initAndroidKoin
import dev.seabat.kmp.firebasestorage.usecase.ActivateAppCheckUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initAndroidKoin {
            androidContext(this@MainApplication)
        }
        val activateAppCheckUseCase: ActivateAppCheckUseCase by inject()
        activateAppCheckUseCase.invoke()
    }
}