package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.BalanceRepository

class RoadBalanceUseCase(
    private val balanceRepository: BalanceRepository
) {
    suspend operator fun invoke(): String {
        return balanceRepository.load()
    }
}