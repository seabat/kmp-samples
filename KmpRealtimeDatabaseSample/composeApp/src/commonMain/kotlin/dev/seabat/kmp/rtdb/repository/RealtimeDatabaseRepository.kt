package dev.seabat.kmp.rtdb.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DataSnapshot
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow

class RealtimeDatabaseRepository {

    fun readUser(userId: String): Flow<DataSnapshot> {
        val database = Firebase.database
        val ref = database.reference("users/$userId")
        return ref.valueEvents
    }

    fun readBalance(userId: String): Flow<DataSnapshot> {
        val database = Firebase.database
        val ref = database.reference("users/$userId/balance")
        return ref.valueEvents
    }

    suspend fun writeUser(userId: String, user: User) {
        val database = Firebase.database
        val ref = database.reference("users")
        val childRef = ref.child(userId)
        childRef.setValue(user)
    }
}