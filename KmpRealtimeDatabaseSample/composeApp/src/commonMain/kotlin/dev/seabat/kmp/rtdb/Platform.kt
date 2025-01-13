package dev.seabat.kmp.rtdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform