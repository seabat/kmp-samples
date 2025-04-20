package dev.seabat.kmp.firebasestorage.datasource

import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.storage.FirebaseStorage
import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult

const val ONE_MEGABYTE: Long = 1024 * 1024

class FirebaseStorageDataSource(
    private val storage: FirebaseStorage
) : FirebaseStorageDataSourceContract {

    override fun fetch(callback: (FirebaseStorageResult) -> Unit) {
        Firebase.appCheck.getToken(true)
            .addOnSuccessListener { token ->
                println("AppCheck Token: ${token.token}")
            }
            .addOnFailureListener { e ->
                println("Failed to get AppCheck token: ${e.message}")
            }

        val noticeRef = storage.reference.child("notice.txt")

        noticeRef.getBytes(ONE_MEGABYTE)
            .addOnSuccessListener { bytes ->
                try {
                    val text = String(bytes)
                    callback(FirebaseStorageResult.Success(text))
                } catch (e: Exception) {
                    callback(
                        FirebaseStorageResult.Error(
                        KmpFirebaseStorageError.FirebaseStorageDataParse(e.message ?: "Failed to parse data from Firebase Storage")
                    ))
                }
            }.addOnFailureListener { exception ->
                callback(
                    FirebaseStorageResult.Error(
                    KmpFirebaseStorageError.FirebaseStorageFailure(exception.message ?: "Failed to access Firebase Storage")
                ))
            }
    }
}