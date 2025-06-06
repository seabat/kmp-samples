package dev.seabat.kmp.withsubmodule.page.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.seabat.kmp.withsubmodule.shared.di.initComposePreviewKoin
import dev.seabat.kmp.tutorial.shared.viewmodel.GreetingSharedViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingScreen(
    name: String = "",
    id: Int = 0,
    goBack: () -> Unit = {}
) {
    val greetingSharedViewModel: GreetingSharedViewModel = koinViewModel()
    val phrases by greetingSharedViewModel.phrases.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        greetingSharedViewModel.loadPhrases()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Greeting")
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Inside Greeting Screen, $name, $id")
            HorizontalDivider()
            phrases.forEach { phrase ->
                Text(phrase)
                HorizontalDivider()
            }
        }
    }
}

@Composable
@Preview
fun GreetingScreenPreview() {
    initComposePreviewKoin()
    MaterialTheme {
        GreetingScreen(name = "", id = 0, {})
    }
}
