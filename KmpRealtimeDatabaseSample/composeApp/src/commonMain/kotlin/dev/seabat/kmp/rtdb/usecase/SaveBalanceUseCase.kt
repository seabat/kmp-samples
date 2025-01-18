package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.BalanceRepository

class SaveBalanceUseCase(
    private val balanceRepository: BalanceRepository
) {
    suspend operator fun invoke(balance: Long) {
        balanceRepository.save(balance.toString())
    }
}