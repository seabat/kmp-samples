package dev.seabat.kmp.firebasestorage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform