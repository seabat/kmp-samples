package dev.seabat.kmp.multimodule.shared.usecase

import dev.seabat.kmp.multimodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.multimodule.shared.util.daysPhrase

class CreatePhrasesUseCase(private val platformRepository: PlatformRepositoryContract) :
    CreatePhrasesUseCaseContract {
    override suspend fun invoke(): List<String> = buildList {
        add("Hello, ${platformRepository.getPlatformName()}!")
        add(daysPhrase())
    }
}