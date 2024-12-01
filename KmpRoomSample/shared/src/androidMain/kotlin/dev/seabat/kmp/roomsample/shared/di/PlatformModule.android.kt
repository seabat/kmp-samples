package dev.seabat.kmp.roomsample.shared.di

import dev.seabat.kmp.roomsample.shared.source.AndroidPlatform
import dev.seabat.kmp.roomsample.shared.source.PlatformContract
import org.koin.dsl.module

actual val platformModule = module {
    single<PlatformContract> {
        AndroidPlatform()
    }
}