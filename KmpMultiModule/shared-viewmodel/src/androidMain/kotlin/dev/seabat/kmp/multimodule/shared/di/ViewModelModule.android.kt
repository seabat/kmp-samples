package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.viewmodel.GreetingSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModel {
        GreetingSharedViewModel(get())
    }
}