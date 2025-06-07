package dev.seabat.kmp.withsubmodule.shared.repository

import dev.seabat.kmp.withsubmodule.shared.util.log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class RocketRepository : RocketRepositoryContract {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        val rockets: List<RocketLaunch> = try {
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        } catch (e: Exception) {
            log("【Main】Caught exception: ${e.message}")
            throw e
        }
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        val date = Instant.parse(lastSuccessLaunch.launchDateUTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        log("【Main】getDateOfLastSuccessfulLaunch: year=${date.year}")
        return "${date.month} ${date.dayOfMonth}, ${date.year}"
    }

    override suspend fun launchPhrase(): String =
        try {
            "【Main】The last successful launch was on ${getDateOfLastSuccessfulLaunch()} 🚀"
        } catch (e: Exception) {
            println("【Main】Exception during getting the date of the last successful launch $e")
            "Error occurred"
        }
}

@Serializable
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?
)