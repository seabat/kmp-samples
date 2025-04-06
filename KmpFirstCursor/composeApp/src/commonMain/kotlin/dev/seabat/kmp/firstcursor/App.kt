package dev.seabat.kmp.firstcursor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        var weatherData by remember { mutableStateOf<WeatherData?>(null) }
        var city by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var error by remember { mutableStateOf<String?>(null) }
        
        val weatherApi = remember { WeatherApi(PlatformConfigProvider()) }
        val scope = rememberCoroutineScope()
        
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("都市名") }
            )
            
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        error = null
                        try {
                            weatherData = weatherApi.getWeather(city)
                        } catch (e: Exception) {
                            error = e.message
                        } finally {
                            isLoading = false
                        }
                    }
                }
            ) {
                Text("天気を取得")
            }

            AnimatedVisibility(weatherData != null) {
                weatherData?.let { data ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("都市: ${data.name}")
                        Text("気温: ${data.main.temp}°C")
                        Text("体感温度: ${data.main.feels_like}°C")
                        Text("湿度: ${data.main.humidity}%")
                        Text("天気: ${data.weather.firstOrNull()?.description ?: ""}")
                    }
                }
            }

            AnimatedVisibility(error != null) {
                Text(
                    text = error ?: "",
                    color = MaterialTheme.colors.error
                )
            }

            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}