package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import dev.seabat.kmp.rtdb.repository.User

class RegisterDatabaseEventUseCase(
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository
) {
    suspend operator fun invoke(userId: String, onCompleted: suspend (String) -> Unit) {
        realtimeDatabaseRepository.readUser(userId).collect { dataSnapshot ->
            if (dataSnapshot.value == null) {
                realtimeDatabaseRepository.writeUser(userId, User(balance = 0))
            }
            onCompleted(userId)
        }
    }
}