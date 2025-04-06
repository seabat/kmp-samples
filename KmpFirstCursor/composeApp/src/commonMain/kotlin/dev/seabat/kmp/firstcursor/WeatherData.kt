package dev.seabat.kmp.firstcursor

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int
)

@Serializable
data class Weather(
    val main: String,
    val description: String
) 