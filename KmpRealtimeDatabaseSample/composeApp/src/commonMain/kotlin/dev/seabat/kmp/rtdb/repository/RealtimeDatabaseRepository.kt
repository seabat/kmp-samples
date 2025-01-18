package dev.seabat.kmp.rtdb.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DataSnapshot
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow

class RealtimeDatabaseRepository {
    fun read(): Flow<DataSnapshot> {
        val database = Firebase.database("https://seabat-dev.firebaseio.com/")
        val ref = database.reference("users/0123456789/balance")
        return ref.valueEvents
    }
}