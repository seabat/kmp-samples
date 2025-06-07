package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.tutorial.shared.di.useCaseModule as subUseCaseModule
import dev.seabat.kmp.tutorial.shared.di.viewModelModule as subViewModelModule
import dev.seabat.kmp.tutorial.shared.di.repositoryModule as subRepositoryModule
import dev.seabat.kmp.tutorial.shared.di.platformModule as subPlatformModule
import dev.seabat.kmp.withsubmodule.shared.di.useCaseModule as mainUseCaseModule
import dev.seabat.kmp.withsubmodule.shared.di.viewModelModule as mainViewModelModule
import dev.seabat.kmp.withsubmodule.shared.di.repositoryModule as mainRepositoryModule
import dev.seabat.kmp.withsubmodule.shared.di.platformModule as mainPlatformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// for Android
fun initAndroidKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        mainUseCaseModule,
        mainViewModelModule,
        mainRepositoryModule,
        mainPlatformModule,
        subUseCaseModule,
        subViewModelModule,
        subRepositoryModule,
        subPlatformModule
    )
}

// for Compose Preview
fun initComposePreviewKoin() = startKoin {
    modules(
        mainUseCaseModule,
        mainViewModelModule,
        mainRepositoryModule,
        mainPlatformModule,
        subUseCaseModule,
        subViewModelModule,
        subRepositoryModule,
        subPlatformModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosKoin() {
    startKoin {
        modules(
            mainUseCaseModule,
            mainViewModelModule,
            mainRepositoryModule,
            mainPlatformModule,
            subUseCaseModule,
            subViewModelModule,
            subRepositoryModule,
            subPlatformModule
        )
    }
}