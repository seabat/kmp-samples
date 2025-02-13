package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.repository.PlatformRepository
import dev.seabat.kmp.multimodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.multimodule.shared.repository.RocketRepository
import dev.seabat.kmp.multimodule.shared.repository.RocketRepositoryContract
import dev.seabat.kmp.multimodule.shared.source.PlatformSource
import dev.seabat.kmp.multimodule.shared.source.PlatformSourceContract
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// for Android
fun initAndroidDataKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        platformModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosDataKoin() {
    startKoin {
        modules(
            repositoryModule,
            platformModule
        )
    }
}

private val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
    single<RocketRepositoryContract> { RocketRepository() }
}
