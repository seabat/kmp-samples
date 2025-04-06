package dev.seabat.kmp.firstcursor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class WeatherApi(private val configProvider: PlatformConfigProvider) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private val baseUrl = "https://api.openweathermap.org/data/2.5"

    suspend fun getWeather(city: String): WeatherData {
        val apiKey = configProvider.getApiKey()
        return client.get("$baseUrl/weather") {
            url {
                parameters.append("q", city)
                parameters.append("appid", apiKey)
                parameters.append("units", "metric")
            }
        }.body()
    }
} 