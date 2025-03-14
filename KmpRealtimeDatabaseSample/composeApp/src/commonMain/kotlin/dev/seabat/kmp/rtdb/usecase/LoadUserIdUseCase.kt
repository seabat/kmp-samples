package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.UserIdRepository

class LoadUserIdUseCase(
    private val userIdRepository: UserIdRepository
) {
    suspend operator fun invoke(): String {
        return userIdRepository.load()
    }
}