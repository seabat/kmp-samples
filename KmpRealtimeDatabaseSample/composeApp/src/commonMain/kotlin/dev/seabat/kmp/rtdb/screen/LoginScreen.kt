package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    goToDatabaseObservation: (userId: String, guid: String, token: String) -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
) {
    var userId by remember { mutableStateOf("") }
    val tokenState by loginViewModel.customToken.collectAsStateWithLifecycle()
    val guidState by loginViewModel.guid.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            enabled = tokenState.isEmpty(),
            value = userId,
            onValueChange = { userId = it },
            label = { Text("ユーザーID") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (guidState.isEmpty()) {
            Button(
                enabled = tokenState.isEmpty(),
                onClick = { loginViewModel.loadGuid() }
            ) {
                Text("GUID生成")
            }
        }
        Text(guidState)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = tokenState.isEmpty() && userId.isNotEmpty() && guidState.isNotEmpty(),
            onClick = {
                loginViewModel.fetchCustomToken(userId = userId)
            }
        ) {
            Text("トークンを取得")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (tokenState.isNotEmpty()) {
            Column {
                Text("取得したトークン: $tokenState")
                Button(
                    onClick = {
                        goToDatabaseObservation(userId, guidState, tokenState)
                        loginViewModel.clearScreen()
                    }
                ) {
                    Text("ホーム画面に遷移")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        goToDatabaseObservation = { _, _, _ ->
        // Do nothing
        }
    )
}