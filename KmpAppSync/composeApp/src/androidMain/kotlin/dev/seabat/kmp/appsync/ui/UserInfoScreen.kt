package dev.seabat.kmp.appsync.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.seabat.kmp.appsync.model.UserInfo
import dev.seabat.kmp.appsync.repository.UserRepository
import kotlinx.coroutines.launch

@Composable
fun UserInfoScreen(
    repository: UserRepository,
    userId: String
) {
    var userInfo by remember { mutableStateOf<UserInfo?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var pointsInput by remember { mutableStateOf("") }
    var isUpdating by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun fetchUserInfo() {
        scope.launch {
            try {
                userInfo = repository.getUserInfo(userId)
                isLoading = false
            } catch (e: Exception) {
                error = e.message
                isLoading = false
            }
        }
    }

    LaunchedEffect(userId) {
        fetchUserInfo()
    }

    LaunchedEffect(userId) {
        repository.observeUserInfo(userId).collect { updatedInfo ->
            userInfo = updatedInfo
            isUpdating = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading -> {
                LoadingContent()
            }
            error != null -> {
                ErrorContent(error = error!!)
            }
            userInfo != null -> {
                UserInfoContent(
                    userInfo = userInfo!!,
                    pointsInput = pointsInput,
                    isUpdating = isUpdating,
                    error = error,
                    onPointsInputChange = { pointsInput = it },
                    onUpdatePoints = { newPoints ->
                        isUpdating = true
                        scope.launch {
                            try {
                                val success = repository.updateUserInfo(
                                    userInfo!!.copy(points = newPoints)
                                )
                                if (!success) {
                                    error = "ポイントの更新に失敗しました"
                                }
                            } catch (e: Exception) {
                                error = e.message
                            }
                        }
                    }
                )
            }
            else -> {
                UserNotFoundContent(
                    userId = userId,
                    onCreateUser = {
                        scope.launch {
                            try {
                                val success = repository.createUserInfo(
                                    UserInfo(
                                        userId = userId,
                                        points = 0
                                    )
                                )
                                if (success) {
                                    fetchUserInfo()
                                } else {
                                    error = "ユーザーの作成に失敗しました"
                                }
                            } catch (e: Exception) {
                                error = e.message
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
private fun ErrorContent(error: String) {
    Text(
        text = "エラーが発生しました: $error",
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
private fun UserInfoContent(
    userInfo: UserInfo,
    pointsInput: String,
    isUpdating: Boolean,
    error: String?,
    onPointsInputChange: (String) -> Unit,
    onUpdatePoints: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInfoCard(userInfo = userInfo)

        Spacer(modifier = Modifier.height(32.dp))

        PointsUpdateForm(
            pointsInput = pointsInput,
            isUpdating = isUpdating,
            error = error,
            onPointsInputChange = onPointsInputChange,
            onUpdatePoints = onUpdatePoints
        )
    }
}

@Composable
private fun UserInfoCard(
    userInfo: UserInfo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ユーザーID: ${userInfo.userId}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ポイント残高: ${userInfo.points}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "作成日時: ${userInfo.createdAt}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "更新日時: ${userInfo.updatedAt}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun PointsUpdateForm(
    pointsInput: String,
    isUpdating: Boolean,
    error: String?,
    onPointsInputChange: (String) -> Unit,
    onUpdatePoints: (Int) -> Unit
) {
    OutlinedTextField(
        value = pointsInput,
        onValueChange = onPointsInputChange,
        label = { Text("新しいポイント") },
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            val newPoints = pointsInput.toIntOrNull()
            if (newPoints != null) {
                onUpdatePoints(newPoints)
            }
        },
        enabled = !isUpdating && pointsInput.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (isUpdating) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text("ポイントを更新")
        }
    }

    if (error != null) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun UserNotFoundContent(
    userId: String,
    onCreateUser: () -> Unit
) {
    Text("ユーザー情報が見つかりません")
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onCreateUser,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text("ユーザーを作成")
    }
} 