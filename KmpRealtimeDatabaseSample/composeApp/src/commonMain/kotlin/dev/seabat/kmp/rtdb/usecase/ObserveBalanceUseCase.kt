package dev.seabat.kmp.rtdb.usecase

import dev.gitlive.firebase.database.DataSnapshot
import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import kotlinx.coroutines.flow.Flow

class ObserveBalanceUseCase(
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository
) {
    operator fun invoke(userId: String, guid: String): Flow<DataSnapshot> {
        return realtimeDatabaseRepository.readBalance(userId, guid)
    }
}