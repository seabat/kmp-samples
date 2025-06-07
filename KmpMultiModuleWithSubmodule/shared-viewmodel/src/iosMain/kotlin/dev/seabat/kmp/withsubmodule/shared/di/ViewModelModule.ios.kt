package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.withsubmodule.shared.viewmodel.GreetingSharedViewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    single {
        GreetingSharedViewModel(get())
    }
}