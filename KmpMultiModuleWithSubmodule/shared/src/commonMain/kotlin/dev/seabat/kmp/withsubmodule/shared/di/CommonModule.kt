package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.tutorial.shared.di.useCaseModule as tutorialUseCaseModule
import dev.seabat.kmp.tutorial.shared.di.viewModelModule as tutorialViewModelModule
import dev.seabat.kmp.tutorial.shared.di.repositoryModule as tutorialRepositoryModule
import dev.seabat.kmp.tutorial.shared.di.platformModule as tutorialPlatformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// for Android
fun initAndroidKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        tutorialUseCaseModule,
        tutorialViewModelModule,
        tutorialRepositoryModule,
        tutorialPlatformModule
    )
}

// for Compose Preview
fun initComposePreviewKoin() = startKoin {
    modules(
        tutorialUseCaseModule,
        tutorialViewModelModule,
        tutorialRepositoryModule,
        tutorialPlatformModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosKoin() {
    startKoin {
        modules(
            tutorialUseCaseModule,
            tutorialViewModelModule,
            tutorialRepositoryModule,
            tutorialPlatformModule
        )
    }
}