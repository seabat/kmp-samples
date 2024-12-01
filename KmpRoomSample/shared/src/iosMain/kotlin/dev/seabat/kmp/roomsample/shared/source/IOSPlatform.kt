package dev.seabat.kmp.roomsample.shared.source

import platform.UIKit.UIDevice

class IOSPlatform : PlatformContract {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}