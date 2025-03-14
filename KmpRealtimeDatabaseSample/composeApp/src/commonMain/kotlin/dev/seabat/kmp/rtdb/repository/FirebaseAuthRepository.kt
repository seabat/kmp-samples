package dev.seabat.kmp.rtdb.repository

import dev.gitlive.firebase.Firebase
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import dev.gitlive.firebase.auth.auth
import dev.seabat.kmp.rtdb.repository.createuserrecord.postCreateUserRecord
import dev.seabat.kmp.rtdb.repository.fetchcustomtoken.postFetchCustomToken


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
            val response = postFetchCustomToken(client, userId)
            response.data.customToken
        } catch (e: Exception) {
            e.printStackTrace()
            "トークンの取得に失敗しました"
        }
    }

    suspend fun createUserRecord(userId: String, guid: String, balance: Long = 0L): String {
        return try {
            val response = postCreateUserRecord(client, userId, guid, balance)
            response.message
        } catch (e: Exception) {
            e.printStackTrace()
            "レコード作成に失敗しました"
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