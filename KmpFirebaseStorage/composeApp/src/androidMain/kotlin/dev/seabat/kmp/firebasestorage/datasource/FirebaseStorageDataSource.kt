package dev.seabat.kmp.firebasestorage.datasource

import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageDataSource(
    private val storage: FirebaseStorage
) : FirebaseStorageDataSourceContract {
    override fun fetch(): String {
        return "Android のお知らせ"
    }
}