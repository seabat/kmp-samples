package dev.seabat.kmp.firebasestorage

import android.app.Application
import dev.seabat.kmp.firebasestorage.di.initAndroidKoin
import dev.seabat.kmp.firebasestorage.usecase.ActivateAppCheckUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

val Application.applicationScope: CoroutineScope
    get() = ApplicationScopeSingleton.scope

private object ApplicationScopeSingleton {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}

class MainApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initAndroidKoin {
            androidContext(this@MainApplication)
        }

        applicationScope.launch {
            val activateAppCheckUseCase: ActivateAppCheckUseCase by inject()
            activateAppCheckUseCase.invoke()
        }
    }
}