package dev.seabat.kmp.firebasestorage.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.seabat.kmp.firebasestorage.di.initComposePreviewKoin
import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpfirebasestorage.composeapp.generated.resources.Res
import kmpfirebasestorage.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val vm = koinViewModel<AppViewModel>()
    val noticeState by vm.notice.collectAsState()
    var showContent by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize().background(color = Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    showContent = !showContent
                    if (showContent) {
                        vm.fetchNotice()
                    }
                }
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Notice: ${
                        when (noticeState) {
                            is FirebaseStorageResult.Success -> (noticeState as FirebaseStorageResult.Success).notice
                            is FirebaseStorageResult.Error -> "" 
                        }
                    }")
                }
            }
        }
        if (noticeState is FirebaseStorageResult.Error) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("エラー") },
                text = { Text((noticeState as FirebaseStorageResult.Error).error.message?: "") },
                confirmButton = {
                    Button(
                        onClick = { vm.clearError() }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    initComposePreviewKoin()

    MaterialTheme {
        App()
    }
}