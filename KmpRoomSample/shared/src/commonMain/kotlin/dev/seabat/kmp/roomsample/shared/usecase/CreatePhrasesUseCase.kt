package dev.seabat.kmp.roomsample.shared.usecase

import dev.seabat.kmp.roomsample.shared.daysPhrase
import dev.seabat.kmp.roomsample.shared.repository.PlatformRepositoryContract

class CreatePhrasesUseCase(private val platformRepository: PlatformRepositoryContract) :
    CreatePhrasesUseCaseContract {
    override suspend fun invoke(): List<String> = buildList {
        add("Hello, ${platformRepository.getPlatformName()}!")
        add(daysPhrase())
    }
}