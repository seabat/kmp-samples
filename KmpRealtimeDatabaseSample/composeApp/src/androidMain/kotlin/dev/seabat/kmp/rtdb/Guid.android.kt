package dev.seabat.kmp.rtdb

import java.util.UUID

actual fun getGuid(): Guid {
    return Guid(UUID.randomUUID().toString())
}