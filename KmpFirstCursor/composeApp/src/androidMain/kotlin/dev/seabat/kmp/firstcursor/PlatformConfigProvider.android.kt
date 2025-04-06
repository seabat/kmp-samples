package dev.seabat.kmp.firstcursor

actual class PlatformConfigProvider {
    actual fun getApiKey(): String {
        return BuildConfig.OPENWEATHERMAP_API_KEY
    }
} 