package dev.seabat.kmp.multimodule.shared.util

actual fun log(message: String) {
    android.util.Log.d("KmpMultiModule", message)
}