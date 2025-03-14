package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    goToDatabaseObservation: (String, String) -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
) {
    var userId by remember { mutableStateOf("") }
    val token by loginViewModel.customToken.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            enabled = token.isEmpty(),
            value = userId,
            onValueChange = { userId = it },
            label = { Text("ユーザーID") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = token.isEmpty(),
            onClick = {
                loginViewModel.fetchCustomToken(userId)
            }
        ) {
            Text("トークンを取得")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (token.isNotEmpty()) {
            Column {
                Text("取得したトークン: $token")
                Button(
                    onClick = {
                        goToDatabaseObservation(token, userId)
                        loginViewModel.clearToken()
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
        goToDatabaseObservation = { _, _ ->
        // Do nothing
        }
    )
}