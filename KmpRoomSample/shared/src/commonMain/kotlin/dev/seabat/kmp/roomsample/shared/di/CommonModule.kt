package dev.seabat.kmp.roomsample.shared.di

import dev.seabat.kmp.roomsample.shared.repository.PlatformRepository
import dev.seabat.kmp.roomsample.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.roomsample.shared.source.PlatformSource
import dev.seabat.kmp.roomsample.shared.source.PlatformSourceContract
import dev.seabat.kmp.roomsample.shared.usecase.CreatePhrasesUseCase
import dev.seabat.kmp.roomsample.shared.usecase.CreatePhrasesUseCaseContract
import dev.seabat.kmp.roomsample.shared.viewmodel.GreetingViewModel
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
private val viewModelModule = module {
    single { GreetingViewModel() }
}

private val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
}

private val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
}
