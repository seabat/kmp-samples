package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract

interface SwiftLibDependencyFactoryContract {
    fun provideFirebaseStorageDataSource(): FirebaseStorageDataSourceContract
}