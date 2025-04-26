package dev.seabat.kmp.appsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dev.seabat.kmp.appsync.repository.UserRepository
import dev.seabat.kmp.appsync.ui.UserInfoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = UserRepository()

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    UserInfoScreen(
                        repository = repository
                    )
                }
            }
        }
    }
}