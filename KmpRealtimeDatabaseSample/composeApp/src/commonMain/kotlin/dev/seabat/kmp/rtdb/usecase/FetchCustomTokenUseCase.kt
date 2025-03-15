package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.flow

class FetchCustomTokenUseCase(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    operator fun invoke(userId: String) =
        flow {
            val customToken = firebaseAuthRepository.fetchCustomToken(userId)
            emit(customToken)
        }
}