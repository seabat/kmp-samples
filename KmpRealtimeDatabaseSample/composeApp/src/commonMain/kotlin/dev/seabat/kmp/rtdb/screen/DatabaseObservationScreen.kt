package dev.seabat.kmp.rtdb.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun DatabaseObservationScreen(
    token: String,
    userId: String,
    guid: String,
    goBack: () -> Boolean,
    viewModel: DatabaseObservationViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Realtime Database レコードを監視中",
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
        Box( modifier = Modifier.padding(paddingValues = paddingValues)) {
            val balanceState by viewModel.balance.collectAsStateWithLifecycle()
            val errorMessageState by viewModel.errorMessage.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.setupDatabase(token = token, userId = userId, guid = guid)
            }

            Column(
                Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text ="残高 ${balanceState}円",
                    fontSize = 20.sp, // フォントサイズを 20.dp に設定
                    fontWeight = FontWeight.Bold // フォントの太さを Bold に設定
                )
                if (errorMessageState.isNotEmpty()) {
                    Text(errorMessageState)
                }
            }
        }
    }
}