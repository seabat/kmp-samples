package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.UserIdRepository

class SaveUserIdUseCase(
    private val userIdRepository: UserIdRepository
) {
    suspend operator fun invoke(userId: String) {
        userIdRepository.save(userId)
    }
}