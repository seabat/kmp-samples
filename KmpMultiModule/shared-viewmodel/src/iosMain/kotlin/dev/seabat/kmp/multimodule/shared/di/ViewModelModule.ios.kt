package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.viewmodel.GreetingSharedViewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    single {
        GreetingSharedViewModel(get())
    }
}