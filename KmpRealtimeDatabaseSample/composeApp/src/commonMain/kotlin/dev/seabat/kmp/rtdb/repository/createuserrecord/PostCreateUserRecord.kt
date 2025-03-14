package dev.seabat.kmp.rtdb.repository.createuserrecord

import dev.seabat.kmp.rtdb.repository.fetchcustomtoken.TokenData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

// Cloud Functions の URL を設定
private const val CLOUD_FUNCTIONS_URL = "https://createuserrecord-36vfxhfxta-dt.a.run.app"

// リクエストボディのデータクラスを定義
@Serializable
private data class RequestBody(val data: Data)

@Serializable
private data class Data(val accountId: String, val guid: String, val balance: Long)

// レスポンスボディのデータクラスを定義
@Serializable
data class CreateUserRecordResponseBody(val message: String)

suspend fun postCreateUserRecord(
    client: HttpClient,
    userId: String,
    guid: String,
    balance: Long
) = client.post(
    CLOUD_FUNCTIONS_URL
) {
    contentType(ContentType.Application.Json)
    setBody(RequestBody(Data(userId, guid, balance)))
}.body() as CreateUserRecordResponseBody