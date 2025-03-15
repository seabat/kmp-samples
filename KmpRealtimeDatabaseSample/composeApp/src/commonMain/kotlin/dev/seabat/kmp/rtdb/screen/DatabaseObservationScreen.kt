package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun DatabaseObservationScreen(
    token: String,
    userId: String,
    guid: String,
    goBack: () -> Boolean,
    viewModel: DatabaseObservationViewModel = viewModel()
) {
    MaterialTheme {
        val balanceState by viewModel.balance.collectAsStateWithLifecycle()
        val errorMessageState by viewModel.errorMessage.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.setupDatabase(token = token, userId = userId, guid = guid)
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("残高 ${balanceState}円")
            if (errorMessageState.isNotEmpty()) {
                Text(errorMessageState)
            }
            Button(
                onClick = { goBack() }
            ) {
                Text("トークン取得画面に戻る")
            }
        }
    }
}