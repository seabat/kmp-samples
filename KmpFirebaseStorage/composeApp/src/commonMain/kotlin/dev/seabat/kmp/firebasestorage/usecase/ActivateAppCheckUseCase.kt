package dev.seabat.kmp.firebasestorage.usecase

import dev.seabat.kmp.firebasestorage.repository.AppCheckRepository

class ActivateAppCheckUseCase(
    private val appCheckRepository: AppCheckRepository
) {
    operator fun invoke() {
        appCheckRepository.activate()
    }
}