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
    viewModel: LoginViewModel = viewModel(),
    newUserId: Boolean,
    newGuid: Boolean,
) {
    val tokenState by viewModel.customToken.collectAsStateWithLifecycle()
    val guidState by viewModel.guid.collectAsStateWithLifecycle()
    val userIdState by viewModel.userId.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (!newUserId) {
            viewModel.loadUserId()
        }
        if (!newGuid) {
            viewModel.loadGuid()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            enabled = tokenState.isEmpty() && newUserId,
            value = userIdState,
            onValueChange = { viewModel.updateUserId(it) },
            label = {
                Text("ユーザーID")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (guidState.isEmpty()) {
            Button(
                enabled = tokenState.isEmpty(),
                onClick = {
                    viewModel.createAndSaveGuid()
                }
            ) {
                Text("GUID生成")
            }
        }
        Text(guidState)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = tokenState.isEmpty() && userIdState.isNotEmpty() && guidState.isNotEmpty(),
            onClick = {
                viewModel.fetchCustomToken(userId = userIdState, createFlag = newUserId || newGuid)
                viewModel.saveUserId(userIdState)
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
                        goToDatabaseObservation(userIdState, guidState, tokenState)
                        viewModel.clearScreen()
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
        },
        newUserId = true,
        newGuid = true
    )
}