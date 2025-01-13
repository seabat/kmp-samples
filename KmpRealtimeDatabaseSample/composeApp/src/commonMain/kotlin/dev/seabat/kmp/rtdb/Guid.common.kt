package dev.seabat.kmp.rtdb

data class Guid(val id: String)

expect fun getGuid(): Guid