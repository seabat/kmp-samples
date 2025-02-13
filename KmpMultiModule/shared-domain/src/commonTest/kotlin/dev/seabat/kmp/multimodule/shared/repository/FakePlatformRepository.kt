package dev.seabat.kmp.multimodule.shared.repository

class FakePlatformRepository : PlatformRepositoryContract {
    override fun getPlatformName(): String = "Android 34"
}