package dev.seabat.kmp.multimodule.shared.usecase

interface CreatePhrasesUseCaseContract {
    suspend operator fun invoke(): List<String>
}