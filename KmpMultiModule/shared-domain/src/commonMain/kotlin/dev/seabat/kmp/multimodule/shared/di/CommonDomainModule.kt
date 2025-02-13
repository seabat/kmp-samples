package dev.seabat.kmp.multimodule.shared.di

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
fun initAndroidDomainKoin() = startKoin {
    modules(
        useCaseModule,
        viewModelModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initIosDomainKoin() {
    startKoin {
        modules(
            useCaseModule,
            viewModelModule
        )
    }
}

// inject を実行するクラスを定義する
private val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
    single<GrepUseCaseContract> { GrepUseCase() }
    single<LoadRocketLaunchInfoUseCaseContract> { LoadRocketLaunchInfoUseCase(get()) }
}
