package dev.seabat.kmp.firebasestorage.datasource

import com.google.firebase.storage.FirebaseStorage

const val ONE_MEGABYTE: Long = 1024 * 1024

class FirebaseStorageDataSource(
    private val storage: FirebaseStorage
) : FirebaseStorageDataSourceContract {

    override fun fetch(callback: (String?, Throwable?) -> Unit) {
        val noticeRef = storage.reference.child("notice.txt")
        noticeRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
            val text = String(bytes)
            println("notice.txtの内容: $text")
            callback(text, null)
        }.addOnFailureListener { exception ->
            // エラー処理
            println("テキストデータのダウンロードに失敗しました: ${exception.message}")
            callback(null, exception)
        }
    }
}