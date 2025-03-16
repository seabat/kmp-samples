package dev.seabat.kmp.firebasestorage.di

import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSource
import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    single {
        FirebaseStorageDataSource(storage = Firebase.storage)
    } bind FirebaseStorageDataSourceContract::class
}
