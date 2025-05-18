package dev.seabat.kmp.withsubmodule.shared.usecase

interface CreatePhrasesUseCaseContract {
    suspend operator fun invoke(): List<String>
}