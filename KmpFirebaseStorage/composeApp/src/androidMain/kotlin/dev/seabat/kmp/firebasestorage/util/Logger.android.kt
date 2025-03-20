package dev.seabat.kmp.firebasestorage.util

actual fun log(message: String) {
    android.util.Log.d("KmpFirebaseStorage", message)
}