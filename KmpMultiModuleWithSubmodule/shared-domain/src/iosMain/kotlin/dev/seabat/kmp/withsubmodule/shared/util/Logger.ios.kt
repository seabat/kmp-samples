package dev.seabat.kmp.withsubmodule.shared.util

import platform.Foundation.NSLog

actual fun log(message: String) {
    NSLog(message)
}