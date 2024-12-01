package dev.seabat.kmp.roomsample.shared.usecase

interface CreatePhrasesUseCaseContract {
    suspend operator fun invoke(): List<String>
}