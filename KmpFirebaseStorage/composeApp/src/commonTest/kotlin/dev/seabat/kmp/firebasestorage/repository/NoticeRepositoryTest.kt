package dev.seabat.kmp.firebasestorage.repository

import kotlin.test.Test

class NoticeRepositoryTest {

    @Test
    fun `Successful fetch with notice`() {
        // Test that fetch returns the notice string when
        // dataSource.fetch successfully retrieves a notice.
        // TODO implement test
    }

    @Test
    fun `Fetch with error message`() {
        // Test that fetch returns the error message string when
        // dataSource.fetch encounters an error.
        // TODO implement test
    }

    @Test
    fun `Fetch with null notice and null error`() {
        // Test that fetch returns an empty string when
        // dataSource.fetch returns a null notice and a null error.
        // TODO implement test
    }

    @Test
    fun `Fetch with null notice and error with empty message`() {
        // Test that fetch returns an empty string when
        // dataSource.fetch returns a null notice and an error with an empty message.
        // TODO implement test
    }

    @Test
    fun `Fetch with empty notice`() {
        // Test that fetch returns the notice string when
        // dataSource.fetch successfully retrieves an empty notice.
        // TODO implement test
    }

    @Test
    fun `Dependency Injection check`() {
        //Test that the FirebaseStorageDataSourceContract is properly
        // injected.
        // TODO implement test
    }

    @Test
    fun `Coroutine Context Switch`() {
        //Test that the resume function of the continuation is performed on the
        //correct coroutine context or thread. It should perform as expected using
        // the runTest
        // TODO implement test
    }

    @Test
    fun `DataSource fetch multiple calls`() {
        //Check multiple calls to the fetch function to ensure state is not corrupted
        // and that each call provides a result as expected.
        // TODO implement test
    }

    @Test
    fun `Fetch with error and non empty message`() {
        // Test that fetch returns the non-empty error message string when
        // dataSource.fetch encounters an error with a non empty message.
        // TODO implement test
    }

    @Test
    fun `Cancelation of coroutine`() {
        // Test that fetch properly handles coroutine cancelation. Verify no
        // resources leak. Also verify no errors are thrown after cancelation
        // TODO implement test
    }

}