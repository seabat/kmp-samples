package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.getGuid
import dev.seabat.kmp.rtdb.repository.GuidRepository

class CreateGuidUseCase(
    private val guidRepository: GuidRepository,
) {
    suspend operator fun invoke(): String {
        val guid = getGuid()
        guidRepository.save(guid.id)
        return guid.id
    }
}


