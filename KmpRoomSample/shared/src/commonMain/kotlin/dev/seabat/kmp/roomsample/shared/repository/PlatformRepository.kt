package dev.seabat.kmp.roomsample.shared.repository

import dev.seabat.kmp.roomsample.shared.source.PlatformSourceContract

class PlatformRepository(private val platformSource: PlatformSourceContract) :
    PlatformRepositoryContract {
    override fun getPlatformName(): String = platformSource.getPlatformName()
}