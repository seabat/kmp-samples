package dev.seabat.kmp.multimodule.shared.repository

interface RocketRepositoryContract {
    suspend fun launchPhrase(): String
}