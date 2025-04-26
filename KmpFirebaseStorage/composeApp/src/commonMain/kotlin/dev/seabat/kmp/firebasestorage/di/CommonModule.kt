package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.repository.AppCheckRepository
import dev.seabat.kmp.firebasestorage.repository.NoticeRepository
import dev.seabat.kmp.firebasestorage.screen.AppViewModel
import dev.seabat.kmp.firebasestorage.usecase.ActivateAppCheckUseCase
import dev.seabat.kmp.firebasestorage.usecase.FetchNoticeUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val useCaseModule = module {
    single { FetchNoticeUseCase(get()) }
    single { ActivateAppCheckUseCase(get()) }
}

val repositoryModule = module {
    single { NoticeRepository() }
    single { AppCheckRepository() }
}

val viewModelModule = module {
    viewModel{ AppViewModel(get()) }
}

// NOTE: platformModule に登録する Module は by inject() で依存を注入する
expect val platformModule: Module

// for Android
fun initAndroidKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        platformModule,
    )
}

// for Compose Preview
fun initComposePreviewKoin() = startKoin {
    modules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        platformModule
    )
}

// for iOS
// CommonModuleKt.doInitIosKoin() として Swift から呼び出す。
fun initIosKoin(onKoinStart: () -> Module) {
    startKoin {
        modules(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            onKoinStart(),
        )
    }
}