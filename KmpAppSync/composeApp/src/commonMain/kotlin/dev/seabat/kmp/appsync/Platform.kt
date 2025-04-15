package dev.seabat.kmp.appsync

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform