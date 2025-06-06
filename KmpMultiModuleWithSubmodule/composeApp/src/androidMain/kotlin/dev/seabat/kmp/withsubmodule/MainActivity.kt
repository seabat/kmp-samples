package dev.seabat.kmp.withsubmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.seabat.kmp.withsubmodule.page.greeting.GreetingScreen
import dev.seabat.kmp.withsubmodule.page.grep.GrepScreen
import dev.seabat.kmp.withsubmodule.page.home.HomeScreen
import dev.seabat.kmp.withsubmodule.page.rocketlaunch.RocketLaunchScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        HomeScreen(
                            goToGreeting = { grepResult ->
                                navController.navigate(Greeting(name = grepResult, id = 10))
                            },
                            goToGrep = {
                                navController.navigate(Grep)
                            },
                            goToRocketLaunch = {
                                navController.navigate(RocketLaunch)
                            }
                        )
                    }
                    composable<Greeting> {
                        val args = it.toRoute<Greeting>()
                        GreetingScreen(
                            name = args.name,
                            id = args.id,
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<Grep> {
                        GrepScreen(
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<RocketLaunch> {
                        RocketLaunchScreen(
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
data class Greeting(
    val name: String,
    val id: Int
)

@Serializable
object Grep

@Serializable
object RocketLaunch

@Preview
@Composable
fun AppAndroidPreview() {
    HomeScreen()
}
