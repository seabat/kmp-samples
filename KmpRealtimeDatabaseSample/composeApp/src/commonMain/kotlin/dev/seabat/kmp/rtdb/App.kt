package dev.seabat.kmp.rtdb

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.seabat.kmp.rtdb.screen.DatabaseObservationScreen
import dev.seabat.kmp.rtdb.screen.LoginScreen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Login
        ) {
            composable<Login> {
                LoginScreen(
                    goToDatabaseObservation = { token, userId ->
                        navController.navigate(DatabaseObservation(token = token, userId = userId))
                    },
                )
            }
            composable<DatabaseObservation> {
                val args = it.toRoute<DatabaseObservation>()
                DatabaseObservationScreen(
                    token = args.token,
                    userId = args.userId,
                    goBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Serializable
object Login

@Serializable
data class DatabaseObservation(
    val token: String,
    val userId: String
)