package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.datasource.FakeFirebaseStorageDataSource
import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun createFakeFirebaseStorageDataSourceModule(
    notice: String?, throwable: Throwable?
): Module {
    return module {
        single {
            FakeFirebaseStorageDataSource(notice, throwable)
        } bind FirebaseStorageDataSourceContract::class
    }
}