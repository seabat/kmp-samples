package dev.seabat.kmp.multimodule.shared.usecase

import kotlinx.coroutines.flow.Flow

interface LoadRocketLaunchInfoUseCaseContract {
    fun getLaunchPhraseFlow(): Flow<String>
}