package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.getGuid

class CreateGuidUseCase {
    operator fun invoke(): String {
        val guid = getGuid()
        return guid.id
    }
}


