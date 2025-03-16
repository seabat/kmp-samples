package dev.seabat.kmp.firebasestorage.util

import platform.Foundation.NSLog

actual fun log(message: String) {
    NSLog(message)
}