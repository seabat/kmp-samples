package dev.seabat.kmp.withsubmodule.shared.source

import platform.UIKit.UIDevice

class IOSPlatform : PlatformContract {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}