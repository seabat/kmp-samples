@file:Suppress("ktlint:standard:filename")

package dev.seabat.kmp.multimodule.shared.usecase

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class GrepUseCaseTest {
    private lateinit var useCase: GrepUseCaseContract

    companion object {
        val sampleData = listOf(
            "123 abc",
            "abc 123",
            "123 ABC",
            "ABC 123"
        )
    }

    @Test
    fun shouldFindLowerCaseLetter() {
        useCase = GrepUseCase()

        val results = mutableListOf<String>()
        useCase(sampleData, "[a-z]+") {
            results.add(it)
        }

        assertEquals(2, results.size)
        for (result in results) {
            assertContains(result, "abc")
        }
    }

    @Test
    fun shouldFindUpperCaseLetter() {
        useCase = GrepUseCase()

        val results = mutableListOf<String>()
        useCase(sampleData, "[A-Z]+") {
            results.add(it)
        }

        assertEquals(2, results.size)
        for (result in results) {
            assertContains(result, "ABC")
        }
    }
}