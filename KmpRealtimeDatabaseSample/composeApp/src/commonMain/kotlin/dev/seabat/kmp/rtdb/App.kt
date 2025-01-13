package dev.seabat.kmp.rtdb

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.seabat.kmp.rtdb.repository.GuidRepository
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmprealtimedatabasesample.composeapp.generated.resources.Res
import kmprealtimedatabasesample.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        val guidRepository = GuidRepository(createDataStore(PlatformContext()))
        val viewModel: AppViewModel = viewModel { AppViewModel(guidRepository) }
        val guidState by viewModel.guid.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.savaGuid("abcdefghij")
        }

        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    showContent = !showContent
                    viewModel.loadGuid()
                }
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("GUID: $guidState")
                }
            }
        }
    }
}