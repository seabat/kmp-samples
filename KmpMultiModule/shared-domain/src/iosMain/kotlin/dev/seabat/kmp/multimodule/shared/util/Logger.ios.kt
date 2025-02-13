package dev.seabat.kmp.multimodule.shared.util

import platform.Foundation.NSLog

actual fun log(message: String) {
    NSLog(message)
}