package dev.seabat.kmp.rtdb.repository

import dev.gitlive.firebase.Firebase
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.serialization.Serializable

// Cloud Functions の URL を設定
private const val CLOUD_FUNCTIONS_URL = "https://fetchcustomtoken-36vfxhfxta-dt.a.run.app"

// リクエストボディのデータクラスを定義
@Serializable
data class RequestBody(val data: Data)

@Serializable
data class Data(val accountId: String)

// レスポンスボディのデータクラスを定義
@Serializable
data class ResponseBody(val data: TokenData)

@Serializable
data class TokenData(val customToken: String)

class FirebaseAuthRepository {

    // Ktor HTTP クライアントを初期化
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    // Cloud Functions を呼び出してカスタムトークンを取得する関数
    suspend fun fetchCustomToken(userId: String): String {
        return try {
            val response: ResponseBody = client.post(CLOUD_FUNCTIONS_URL) {
                contentType(ContentType.Application.Json)
                setBody(RequestBody(Data(userId)))
            }.body()
            response.data.customToken
        } catch (e: Exception) {
            e.printStackTrace()
            "トークンの取得に失敗しました"
        }
    }

    suspend fun signInWithCustomToken(token: String): Boolean {
        val auth = Firebase.auth
        return try {
            val authResult = auth.signInWithCustomToken(token)
            println("Firebaseにサインイン成功: ${authResult.user?.uid}")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            println("Firebaseサインインエラー: ${e.message}")
            false
        }
    }
}