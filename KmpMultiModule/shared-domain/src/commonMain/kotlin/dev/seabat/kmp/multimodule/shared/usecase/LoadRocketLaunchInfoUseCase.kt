package dev.seabat.kmp.multimodule.shared.usecase

import dev.seabat.kmp.multimodule.shared.repository.RocketRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadRocketLaunchInfoUseCase(
    private val rocketRepository: RocketRepositoryContract
) : LoadRocketLaunchInfoUseCaseContract {

    override operator fun invoke(): Flow<String> = flow {
        emit(rocketRepository.launchPhrase())
    }
}