package dev.seabat.kmp.multimodule.shared.repository

import dev.seabat.kmp.multimodule.shared.source.PlatformSourceContract

class PlatformRepository(private val platformSource: PlatformSourceContract) :
    PlatformRepositoryContract {
    override fun getPlatformName(): String = platformSource.getPlatformName()
}