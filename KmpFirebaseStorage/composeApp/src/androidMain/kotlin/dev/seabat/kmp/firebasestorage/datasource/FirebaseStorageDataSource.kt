package dev.seabat.kmp.firebasestorage.datasource

import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageDataSource(
    private val storage: FirebaseStorage
) : FirebaseStorageDataSourceContract {
    override fun fetch(callback: (String?, Throwable?) -> Unit) {
        return callback("Androidのお知らせ", null)
    }
}