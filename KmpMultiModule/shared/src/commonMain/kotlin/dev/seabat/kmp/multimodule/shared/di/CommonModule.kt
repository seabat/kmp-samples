package dev.seabat.kmp.multimodule.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// for Android
fun initAndroidKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        useCaseModule,
        viewModelModule,
        repositoryModule,
        platformModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosKoin() {
    startKoin {
        modules(
            useCaseModule,
            viewModelModule,
            repositoryModule,
            platformModule
        )
    }
}