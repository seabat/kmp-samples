package dev.seabat.kmp.rtdb

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.seabat.kmp.rtdb.screen.DatabaseObservationScreen
import dev.seabat.kmp.rtdb.screen.InitScreen
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
            startDestination = Init
        ) {
            composable<Init> {
                InitScreen(
                    goToLogin = { newUserId, newGuid ->
                        navController.navigate(Login(newUserId = newUserId, newGuid = newGuid))
                    },
                )
            }
            composable<Login> {
                LoginScreen(
                    newUserId = it.toRoute<Login>().newUserId,
                    newGuid = it.toRoute<Login>().newGuid,
                    goToDatabaseObservation = { userId, guid, token ->
                        navController.navigate(DatabaseObservation(token = token, userId = userId, guid = guid))
                    },
                )
            }
            composable<DatabaseObservation> {
                val args = it.toRoute<DatabaseObservation>()
                DatabaseObservationScreen(
                    token = args.token,
                    userId = args.userId,
                    guid = args.guid,
                    goBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Serializable
object Init

@Serializable
data class Login(
    val newUserId: Boolean,
    val newGuid: Boolean
)
@Serializable
data class DatabaseObservation(
    val token: String,
    val userId: String,
    val guid: String
)