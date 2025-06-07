package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.withsubmodule.shared.repository.PlatformRepository
import dev.seabat.kmp.withsubmodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.withsubmodule.shared.repository.RocketRepository
import dev.seabat.kmp.withsubmodule.shared.repository.RocketRepositoryContract
import dev.seabat.kmp.withsubmodule.shared.source.PlatformSource
import dev.seabat.kmp.withsubmodule.shared.source.PlatformSourceContract
import org.koin.dsl.module

// inject を実行するクラスを定義する
val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
    single<RocketRepositoryContract> { RocketRepository() }
}
