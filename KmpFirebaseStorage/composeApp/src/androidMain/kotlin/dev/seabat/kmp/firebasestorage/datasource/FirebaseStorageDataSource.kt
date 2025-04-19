package dev.seabat.kmp.firebasestorage.datasource

import android.util.Log
import com.google.firebase.storage.FirebaseStorage

private const val TAG = "FirebaseStorage"
const val ONE_MEGABYTE: Long = 1024 * 1024

class FirebaseStorageDataSource(
    private val storage: FirebaseStorage
) : FirebaseStorageDataSourceContract {

    override fun fetch(callback: (String?, Throwable?) -> Unit) {
        val noticeRef = storage.reference.child("notice.txt")
        noticeRef.getBytes(ONE_MEGABYTE)
            .addOnSuccessListener { bytes ->
                try {
                    val text = String(bytes)
                    callback(text, null)
                } catch (e: Exception) {
                    callback(null, FirebaseStorageError.DataParseError(e.message ?: "Failed to parse data"))
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "Storage error: ${exception.message}")
                callback(null, FirebaseStorageError.NetworkError(exception.message ?: "Network error occurred"))
            }
    }
}