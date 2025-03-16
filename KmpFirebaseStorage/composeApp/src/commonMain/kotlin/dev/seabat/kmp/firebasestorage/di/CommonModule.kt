package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSource
import dev.seabat.kmp.firebasestorage.repository.NoticeRepository
import dev.seabat.kmp.firebasestorage.screen.AppViewModel
import dev.seabat.kmp.firebasestorage.usecase.FetchNoticeUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val useCaseModule = module {
    single { FetchNoticeUseCase(get()) }
}

val repositoryModule = module {
    single { NoticeRepository(get()) }
}

val dataSourceModule = module {
    single { FirebaseStorageDataSource() }
}

val viewModelModule = module {
    viewModel{ AppViewModel(get()) }
}

// for Android
fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initKoin() {
    startKoin {
        modules(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    }
}