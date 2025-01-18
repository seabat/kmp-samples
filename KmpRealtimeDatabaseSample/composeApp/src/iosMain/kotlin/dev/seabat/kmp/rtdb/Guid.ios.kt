package dev.seabat.kmp.rtdb

import platform.Foundation.NSUUID

actual fun getGuid(): Guid {
    return Guid(NSUUID().UUIDString())
}