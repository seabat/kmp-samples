package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.withsubmodule.shared.usecase.CreatePhrasesUseCase
import dev.seabat.kmp.withsubmodule.shared.usecase.CreatePhrasesUseCaseContract
import dev.seabat.kmp.withsubmodule.shared.usecase.GrepUseCase
import dev.seabat.kmp.withsubmodule.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.withsubmodule.shared.usecase.LoadRocketLaunchInfoUseCase
import dev.seabat.kmp.withsubmodule.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import org.koin.dsl.module

// inject を実行するクラスを定義する
val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
    single<GrepUseCaseContract> { GrepUseCase() }
    single<LoadRocketLaunchInfoUseCaseContract> { LoadRocketLaunchInfoUseCase(get()) }
}
