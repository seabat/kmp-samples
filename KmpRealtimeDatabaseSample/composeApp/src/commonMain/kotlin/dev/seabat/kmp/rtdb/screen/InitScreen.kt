package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InitScreen(
    goToLogin: (newUserId: Boolean, newGuid: Boolean) -> Unit,
    viewModel: InitViewModel = viewModel(),
) {

    val hasGuid by viewModel.hasGuid.collectAsStateWithLifecycle()
    val hasUserId by viewModel.hasUserId.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadUserId()
        viewModel.loadGuid()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
        ) {
            Button(
                onClick = { goToLogin(true, true) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("新しいユーザーのレコード監視する")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = hasUserId,
                onClick = { goToLogin(false, true) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("前回ユーザー＋新しいGUIDのレコード監視する")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = hasUserId && hasGuid,
                onClick = { goToLogin(false, false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("前回ユーザー＋前回GUID のレコード監視する")
            }
        }
    }
}