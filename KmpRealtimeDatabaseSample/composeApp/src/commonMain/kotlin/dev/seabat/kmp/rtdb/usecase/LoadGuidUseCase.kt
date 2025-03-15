package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.GuidRepository

class LoadGuidUseCase(
    private val guidRepository: GuidRepository,
) {
    suspend operator fun invoke(): String {
        return guidRepository.load()
    }
}