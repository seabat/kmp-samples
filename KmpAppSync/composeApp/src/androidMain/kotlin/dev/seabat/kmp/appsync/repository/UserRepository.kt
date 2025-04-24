package dev.seabat.kmp.appsync.repository

import com.amplifyframework.core.Amplify
import com.amplifyframework.api.graphql.model.ModelSubscription
import dev.seabat.kmp.appsync.model.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import android.util.Log
import com.amplifyframework.api.graphql.SimpleGraphQLRequest
import com.amplifyframework.api.graphql.GraphQLRequest
import com.google.gson.Gson
import com.amplifyframework.core.model.temporal.Temporal

open class UserRepository() {

    open suspend fun getUserInfo(userId: String): UserInfo? {
        if (userId.isBlank()) {
            Log.e("UserRepository", "userId is blank")
            return null
        }
        return try {
            val query = """
                query GetUserInfo(${'$'}userId: ID!) {
                    getUserInfo(userId: ${'$'}userId) {
                        userId
                        points
                        createdAt
                        updatedAt
                        __typename
                    }
                }
            """.trimIndent()

            val variables = mapOf("userId" to userId)

            val request = SimpleGraphQLRequest<Map<String, Any>>(
                query,
                variables,
                Map::class.java,
                object : GraphQLRequest.VariablesSerializer {
                    override fun serialize(variables: Map<String, Any>): String {
                        return Gson().toJson(variables)
                    }
                }
            )

            suspendCoroutine { continuation ->
                Amplify.API.query(
                    request,
                    { response ->
                        Log.d("UserRepository", "Query response: $response")
                        if (response.data != null) {
                            val userData = response.data["getUserInfo"] as? Map<String, Any>
                            if (userData != null) {
                                val userInfo = UserInfo(
                                    userId = userData["userId"] as String,
                                    points = (userData["points"] as Number).toInt(),
                                    createdAt = Temporal.DateTime((userData["createdAt"] as String)),
                                    updatedAt = Temporal.DateTime((userData["updatedAt"] as String))
                                )
                                continuation.resume(userInfo)
                            } else {
                                continuation.resume(null)
                            }
                        } else {
                            Log.e("UserRepository", "Query failed: ${response.errors}")
                            continuation.resume(null)
                        }
                    },
                    { error ->
                        Log.e("UserRepository", "Query error", error)
                        continuation.resumeWithException(error)
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Query exception", e)
            null
        }
    }

    open suspend fun updateUserInfo(userInfo: UserInfo): Boolean {
        return try {
            val mutation = """
                mutation UpdateUserInfo(${'$'}input: UpdateUserInfoInput!) {
                    updateUserInfo(input: ${'$'}input) {
                        userId
                        points
                        createdAt
                        updatedAt
                        __typename
                    }
                }
            """.trimIndent()

            val variables = mapOf(
                "input" to mapOf(
                    "userId" to userInfo.userId,
                    "points" to userInfo.points
                )
            )

            val request = SimpleGraphQLRequest<Map<String, Any>>(
                mutation,
                variables,
                Map::class.java,
                object : GraphQLRequest.VariablesSerializer {
                    override fun serialize(variables: Map<String, Any>): String {
                        return Gson().toJson(variables)
                    }
                }
            )

            suspendCoroutine { continuation ->
                Amplify.API.mutate(
                    request,
                    { response ->
                        Log.d("UserRepository", "Update response: $response")
                        if (response.data != null) {
                            continuation.resume(true)
                        } else {
                            Log.e("UserRepository", "Update failed: ${response.errors}")
                            continuation.resume(false)
                        }
                    },
                    { error ->
                        Log.e("UserRepository", "Update error", error)
                        continuation.resumeWithException(error)
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Update exception", e)
            false
        }
    }

    open suspend fun createUserInfo(userInfo: UserInfo): Boolean {
        return try {
            val mutation = """
                mutation CreateUserInfo {
                    createUserInfo(input: {
                        userId: "${userInfo.userId}",
                        points: ${userInfo.points}
                    }) {
                        userId
                        points
                        createdAt
                        updatedAt
                    }
                }
            """.trimIndent()

            val request = SimpleGraphQLRequest<Map<String, Any>>(
                mutation,
                Map::class.java,
                object : GraphQLRequest.VariablesSerializer {
                    override fun serialize(variables: Map<String, Any>): String {
                        return Gson().toJson(variables)
                    }
                }
            )

            Log.d("UserRepository", "Creating user info: $userInfo")
            suspendCoroutine { continuation ->
                Amplify.API.mutate(request,
                    { response ->
                        Log.d("UserRepository", "Create response: $response")
                        if (response.data != null) {
                            continuation.resume(true)
                        } else {
                            Log.e("UserRepository", "Create failed: ${response.errors}")
                            continuation.resume(false)
                        }
                    },
                    { error ->
                        Log.e("UserRepository", "Create error", error)
                        continuation.resumeWithException(error)
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Create exception", e)
            false
        }
    }

    open fun observeUserInfo(userId: String): Flow<UserInfo?> = callbackFlow {
        try {
            val operation = Amplify.API.subscribe(
                ModelSubscription.onUpdate(UserInfo::class.java),
                { Log.d("UserRepository", "Subscription started") },
                { event ->
                    Log.d("UserRepository", "Subscription event: $event")
                    if (event.data != null) {
                        trySendBlocking(event.data)
                    }
                },
                { error -> 
                    Log.e("UserRepository", "Subscription error", error)
                    trySendBlocking(null) 
                },
                { Log.d("UserRepository", "Subscription completed") }
            )

            awaitClose {
                operation?.cancel()
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Subscription exception", e)
            trySendBlocking(null)
            close(e)
        }
    }
} 