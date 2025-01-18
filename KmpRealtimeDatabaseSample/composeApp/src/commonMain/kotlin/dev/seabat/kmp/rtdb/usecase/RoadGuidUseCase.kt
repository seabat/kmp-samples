package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.GuidRepository

class RoadGuidUseCase(
    private val guidRepository: GuidRepository,
    private val createGuidUseCase: CreateGuidUseCase
) {
    suspend operator fun invoke(): String {
        return guidRepository.load().let { guid ->
            if (guid == "") {
                val newGuid = createGuidUseCase()
                guidRepository.save(newGuid)
                newGuid
            } else {
                guid
            }
        }
    }
}