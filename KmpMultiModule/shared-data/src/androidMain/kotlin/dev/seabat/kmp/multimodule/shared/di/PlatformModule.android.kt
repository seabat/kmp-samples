package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.source.AndroidPlatform
import dev.seabat.kmp.multimodule.shared.source.PlatformContract
import org.koin.dsl.module

actual val platformModule = module {
    single<PlatformContract> {
        AndroidPlatform()
    }
}