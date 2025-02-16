package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.usecase.CreatePhrasesUseCase
import dev.seabat.kmp.multimodule.shared.usecase.CreatePhrasesUseCaseContract
import dev.seabat.kmp.multimodule.shared.usecase.GrepUseCase
import dev.seabat.kmp.multimodule.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.multimodule.shared.usecase.LoadRocketLaunchInfoUseCase
import dev.seabat.kmp.multimodule.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import org.koin.dsl.module

// inject を実行するクラスを定義する
val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
    single<GrepUseCaseContract> { GrepUseCase() }
    single<LoadRocketLaunchInfoUseCaseContract> { LoadRocketLaunchInfoUseCase(get()) }
}
