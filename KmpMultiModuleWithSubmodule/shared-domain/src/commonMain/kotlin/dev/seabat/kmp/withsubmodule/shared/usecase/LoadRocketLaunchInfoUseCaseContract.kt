package dev.seabat.kmp.withsubmodule.shared.usecase

import kotlinx.coroutines.flow.Flow

interface LoadRocketLaunchInfoUseCaseContract {
    operator fun invoke(): Flow<String>
}