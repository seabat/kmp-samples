package dev.seabat.kmp.roomsample.shared.source

class PlatformSource(private val platform: PlatformContract) : PlatformSourceContract {
    override fun getPlatformName(): String = platform.name
}