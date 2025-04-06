package dev.seabat.kmp.firstcursor

import java.io.File
import java.util.Properties

actual class PlatformConfigProvider {
    actual fun getApiKey(): String {
        // 直接APIキーを返す
        return "YOUR_API_KEY"
    }
} 