package dev.seabat.kmp.withsubmodule.shared.repository

interface RocketRepositoryContract {
    suspend fun launchPhrase(): String
}