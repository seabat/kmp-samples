package dev.seabat.kmp.withsubmodule.shared.usecase

import dev.seabat.kmp.withsubmodule.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.withsubmodule.shared.util.daysPhrase

class CreatePhrasesUseCase(private val platformRepository: PlatformRepositoryContract) :
    CreatePhrasesUseCaseContract {
    override suspend fun invoke(): List<String> = buildList {
        add("Hello, ${platformRepository.getPlatformName()}!")
        add(daysPhrase())
    }
}