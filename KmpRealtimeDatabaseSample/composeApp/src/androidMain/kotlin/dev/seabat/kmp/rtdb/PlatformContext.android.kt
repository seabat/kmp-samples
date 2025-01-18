package dev.seabat.kmp.rtdb

import android.content.Context

actual class PlatformContext actual constructor() {
    var context: Context = RtdbApplication.getApplicationInstance().applicationContext
}

