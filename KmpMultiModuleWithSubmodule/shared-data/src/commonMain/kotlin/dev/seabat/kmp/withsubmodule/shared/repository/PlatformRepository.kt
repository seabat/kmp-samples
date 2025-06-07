package dev.seabat.kmp.withsubmodule.shared.repository

import dev.seabat.kmp.withsubmodule.shared.source.PlatformSourceContract

class PlatformRepository(private val platformSource: PlatformSourceContract) :
    PlatformRepositoryContract {
    override fun getPlatformName(): String = platformSource.getPlatformName()
}