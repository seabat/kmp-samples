package dev.seabat.kmp.withsubmodule.shared.util

actual fun log(message: String) {
    android.util.Log.d("KmpMultiModuleWithSubmodule", message)
}