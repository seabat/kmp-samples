package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    newUserId: Boolean,
    newGuid: Boolean,
    viewModel: LoginViewModel = viewModel(),
    goBack: () -> Unit,
    goToDatabaseObservation: (userId: String, guid: String, token: String) -> Unit
) {

    LaunchedEffect(Unit) {
        if (!newUserId) {
            viewModel.loadUserId()
        }
        if (!newGuid) {
            viewModel.loadGuid()
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Firebase トークンの発行&取得",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis // 表示しきれない部分を省略し、末尾に"..." を付ける
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Content(goToDatabaseObservation, viewModel, newUserId, newGuid)
        }
    }
}

@Composable
private fun Content(
    goToDatabaseObservation: (userId: String, guid: String, token: String) -> Unit,
    viewModel: LoginViewModel,
    newUserId: Boolean,
    newGuid: Boolean,
) {
    val tokenState by viewModel.customToken.collectAsStateWithLifecycle()
    val guidState by viewModel.guid.collectAsStateWithLifecycle()
    val userIdState by viewModel.userId.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = tokenState.isEmpty() && newUserId,
            value = userIdState,
            onValueChange = { viewModel.updateUserId(it) },
            label = {
                Text("ユーザーID")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // GUID
        Button(
            enabled = guidState.isEmpty(),
            onClick = {
                viewModel.createAndSaveGuid()
            }
        ) {
            Text("GUID生成")
        }
        if (guidState.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "GUID: $guidState"
            )
        }

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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "取得したトークン: $tokenState"
                )
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
        newUserId = true,
        newGuid = true,
        goBack = {},
        goToDatabaseObservation = { _, _, _ ->
            // Do nothing
        }
    )
}