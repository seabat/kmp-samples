package dev.seabat.kmp.roomsample.shared.repository

import dev.seabat.kmp.roomsample.shared.source.FakePlatformSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformRepositoryTest {
    @Test
    fun testGetPlatformName() {
        val repository = PlatformRepository(FakePlatformSource())
        val platformName = repository.getPlatformName()
        assertEquals(true, platformName.isNotEmpty())
    }
}