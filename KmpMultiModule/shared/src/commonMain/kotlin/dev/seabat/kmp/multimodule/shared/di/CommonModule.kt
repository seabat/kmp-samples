package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.repository.PlatformRepository
import dev.seabat.kmp.multimodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.multimodule.shared.repository.RocketRepository
import dev.seabat.kmp.multimodule.shared.repository.RocketRepositoryContract
import dev.seabat.kmp.multimodule.shared.source.PlatformSource
import dev.seabat.kmp.multimodule.shared.source.PlatformSourceContract
import dev.seabat.kmp.multimodule.shared.usecase.CreatePhrasesUseCase
import dev.seabat.kmp.multimodule.shared.usecase.CreatePhrasesUseCaseContract
import dev.seabat.kmp.multimodule.shared.usecase.GrepUseCase
import dev.seabat.kmp.multimodule.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.multimodule.shared.usecase.LoadRocketLaunchInfoUseCase
import dev.seabat.kmp.multimodule.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import dev.seabat.kmp.multimodule.shared.viewmodel.GreetingSharedViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// for Android
fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        useCaseModule,
        repositoryModule,
        viewModelModule,
        platformModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initKoin() {
    startKoin {
        modules(
            useCaseModule,
            repositoryModule,
            viewModelModule,
            platformModule
        )
    }
}

// inject を実行するクラスを定義する
private val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
    single<GrepUseCaseContract> { GrepUseCase() }
    single<LoadRocketLaunchInfoUseCaseContract> { LoadRocketLaunchInfoUseCase(get()) }
}

private val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
    single<RocketRepositoryContract> { RocketRepository() }
}
