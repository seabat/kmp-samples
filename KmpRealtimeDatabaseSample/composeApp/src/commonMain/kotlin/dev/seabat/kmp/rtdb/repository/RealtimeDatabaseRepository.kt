package dev.seabat.kmp.rtdb.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DataSnapshot
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow

class RealtimeDatabaseRepository {

    fun readBalance(userId: String, guid: String): Flow<DataSnapshot> {
        val database = Firebase.database
        val ref = database.reference("users/$userId/$guid/balance")
        return ref.valueEvents
    }
}