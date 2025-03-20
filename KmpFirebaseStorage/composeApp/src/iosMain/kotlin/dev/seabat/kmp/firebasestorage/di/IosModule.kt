package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    // NOTE: FirebaseStorageDataSource はここでは登録せず、initKoin の
    //       コールバックを介して swiftLibDependenciesModule() をコールすることで登録する。
}

fun createSwiftLibDependencyModule(factory: SwiftLibDependencyFactoryContract): Module = module {
    single { factory.provideFirebaseStorageDataSource() } bind FirebaseStorageDataSourceContract::class
}