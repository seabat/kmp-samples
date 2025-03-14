package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.flow

class CreateUserRecordUseCase(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    operator fun invoke(userId: String, guid: String, balance: Long = 0L) =
        flow {
            val result = firebaseAuthRepository.createUserRecord(userId, guid, balance)
            emit(result)
        }
}