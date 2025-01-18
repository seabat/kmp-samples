package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import dev.seabat.kmp.rtdb.repository.User

class RegisterDatabaseEventUseCase(
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository
) {
    suspend operator fun invoke(id: String, onCompleted: suspend (String) -> Unit) {
        realtimeDatabaseRepository.readUser(id).collect { dataSnapshot ->
            if (dataSnapshot.value == null) {
                realtimeDatabaseRepository.writeUser(id, User(balance = 0))
            }
            onCompleted(id)
        }
    }
}