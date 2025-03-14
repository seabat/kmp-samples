package dev.seabat.kmp.rtdb.repository.fetchcustomtoken

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

// Cloud Functions の URL を設定
private const val CLOUD_FUNCTIONS_URL = "https://fetchcustomtoken-36vfxhfxta-dt.a.run.app"

// リクエストボディのデータクラスを定義
@Serializable
private data class RequestBody(val data: Data)

@Serializable
private data class Data(val accountId: String)

// レスポンスボディのデータクラスを定義
@Serializable
data class TokenData(val customToken: String)

@Serializable
data class FetchCustomTokenResponseBody(val data: TokenData)

suspend fun postFetchCustomToken(client: HttpClient, userId: String) = client.post(
    CLOUD_FUNCTIONS_URL
) {
        contentType(ContentType.Application.Json)
        setBody(RequestBody(Data(userId)))
    }.body() as FetchCustomTokenResponseBody
