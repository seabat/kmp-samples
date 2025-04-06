package dev.seabat.kmp.firstcursor

import platform.Foundation.NSBundle

actual class PlatformConfigProvider {
    actual fun getApiKey(): String {
        val bundle = NSBundle.mainBundle
        val apiKey = bundle.objectForInfoDictionaryKey("OpenWeatherMapApiKey") as? String
            ?: throw IllegalStateException("API key not found in Info.plist")
            
        if (apiKey == "YOUR_API_KEY") {
            throw IllegalStateException("""
                Please set your actual API key in Config.xcconfig.
                Replace YOUR_API_KEY with your actual OpenWeatherMap API key
            """.trimIndent())
        }
        
        return apiKey
    }
} 