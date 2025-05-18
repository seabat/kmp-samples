package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.withsubmodule.shared.source.AndroidPlatform
import dev.seabat.kmp.withsubmodule.shared.source.PlatformContract
import org.koin.dsl.module

actual val platformModule = module {
    single<PlatformContract> {
        AndroidPlatform()
    }
}