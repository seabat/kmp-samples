package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.di.createFakeFirebaseStorageDataSourceModule
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class NoticeRepositoryTest {

    @BeforeTest
    fun setUp() {}

    @Test
    fun `Successful fetch with notice`() {
        startKoin {
            modules(
                createFakeFirebaseStorageDataSourceModule(
                    "abc", null
                )
            )
        }

        runTest {
            val result = NoticeRepository().fetch()
            assertEquals("abc", result)
        }
    }

    @Test
    fun `Fetch with error message`() {
        startKoin {
            modules(
                createFakeFirebaseStorageDataSourceModule(
                    null, Throwable("error")
                )
            )
        }
        runTest {
            val result = NoticeRepository().fetch()
            assertEquals("error", result)
        }
    }

    @Test
    fun `Fetch with null notice and null error`() {
        startKoin {
            modules(
                createFakeFirebaseStorageDataSourceModule(
                    null, null
                )
            )
        }
        runTest {
            val result = NoticeRepository().fetch()
            assertEquals("", result)
        }
    }

    @Test
    fun `Fetch with null notice and error with empty message`() {
        startKoin {
            modules(
                createFakeFirebaseStorageDataSourceModule(
                    null, Throwable("")
                )
            )
        }
        runTest {
            val result = NoticeRepository().fetch()
            assertEquals("", result)
        }
    }

    @Test
    fun `Fetch with empty notice`() {
        startKoin {
            modules(
                createFakeFirebaseStorageDataSourceModule(
                    "", null
                )
            )
        }
        runTest {
            val result = NoticeRepository().fetch()
            assertEquals("", result)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}