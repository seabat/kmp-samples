package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.repository.PlatformRepository
import dev.seabat.kmp.multimodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.multimodule.shared.repository.RocketRepository
import dev.seabat.kmp.multimodule.shared.repository.RocketRepositoryContract
import dev.seabat.kmp.multimodule.shared.source.PlatformSource
import dev.seabat.kmp.multimodule.shared.source.PlatformSourceContract
import org.koin.dsl.module

// inject を実行するクラスを定義する
val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
    single<RocketRepositoryContract> { RocketRepository() }
}
