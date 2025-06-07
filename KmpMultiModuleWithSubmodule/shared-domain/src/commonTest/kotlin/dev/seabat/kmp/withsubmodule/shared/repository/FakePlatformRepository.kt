package dev.seabat.kmp.withsubmodule.shared.repository

class FakePlatformRepository : PlatformRepositoryContract {
    override fun getPlatformName(): String = "Android 34"
}