package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository

class SignInWithCustomTokenUseCase(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke(customToken: String): Boolean {
        return firebaseAuthRepository.signInWithCustomToken(customToken)
    }
}