package dev.seabat.kmp.multimodule.shared.usecase

import RocketComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadRocketLaunchInfoUseCase : LoadRocketLaunchInfoUseCaseContract{
    private val rocketComponent by lazy { RocketComponent() }
    override fun getLaunchPhraseFlow(): Flow<String> = flow {
        emit(rocketComponent.launchPhrase())
    }
}