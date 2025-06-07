package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.tutorial.shared.di.koinModules as subKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

val koinModules = listOf(
    viewModelModule,
    useCaseModule,
    repositoryModule,
    platformModule
)

// for Android
fun initAndroidKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(koinModules + subKoinModules)
}

// for Compose Preview
fun initComposePreviewKoin() = startKoin {
    modules(koinModules + subKoinModules)
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosKoin() {
    startKoin {
        modules(koinModules + subKoinModules)
    }
}