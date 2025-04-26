package dev.seabat.kmp.appsync.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.seabat.kmp.appsync.model.UserInfo
import dev.seabat.kmp.appsync.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockUserRepository : UserRepository() {
    override suspend fun getUserInfo(userId: String): UserInfo? {
        return UserInfo(userId = "user123", points = 100)
    }

    override suspend fun createUserInfo(userInfo: UserInfo): Boolean {
        return true
    }

    override suspend fun updateUserInfo(userInfo: UserInfo): Boolean {
        return true
    }

    override fun observeUserInfo(userId: String): Flow<UserInfo?> {
        return flowOf(UserInfo(userId = "user123", points = 100))
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    val context = LocalContext.current
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            UserInfoScreen(
                repository = MockUserRepository()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenEmptyPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            UserInfoScreen(
                repository = object : UserRepository() {
                    override suspend fun getUserInfo(userId: String): UserInfo? = null
                    override suspend fun createUserInfo(userInfo: UserInfo): Boolean = true
                    override suspend fun updateUserInfo(userInfo: UserInfo): Boolean = true
                    override fun observeUserInfo(userId: String): Flow<UserInfo?> = flowOf(null)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenErrorPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            UserInfoScreen(
                repository = object : UserRepository() {
                    override suspend fun getUserInfo(userId: String): UserInfo? {
                        throw Exception("エラーが発生しました")
                    }
                    override suspend fun createUserInfo(userInfo: UserInfo): Boolean = true
                    override suspend fun updateUserInfo(userInfo: UserInfo): Boolean = true
                    override fun observeUserInfo(userId: String): Flow<UserInfo?> = flowOf(null)
                }
            )
        }
    }
} 