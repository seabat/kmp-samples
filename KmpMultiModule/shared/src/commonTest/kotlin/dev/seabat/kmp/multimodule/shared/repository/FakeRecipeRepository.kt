package dev.seabat.kmp.multimodule.shared.repository

class FakeRecipeRepository : PlatformRepositoryContract {
    override fun getPlatformName(): String = "Android 34"
}