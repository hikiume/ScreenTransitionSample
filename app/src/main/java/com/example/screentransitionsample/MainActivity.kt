package com.example.screentransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screentransitionsample.ui.theme.ScreenTransitionSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenTransitionSampleTheme {
                MyAppNavigation()
            }
        }
    }
}

object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail"
}

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        composable(Destinations.HOME) {
            HomeScreen(
                onNavigateToDetail = {
                    navController.navigate(Destinations.DETAIL)
                }
            )
        }

        composable(Destinations.DETAIL) {
            DetailScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun HomeScreen(onNavigateToDetail: () -> Unit) {
    Column() {
        Button(onClick = onNavigateToDetail) {
            Text("詳細画面へ移動")
        }
    }
}

@Composable
fun DetailScreen(onBack: () -> Unit) {
    Column() {
        Button(onClick = onBack) {
            Text("ホーム画面に戻る")
        }
    }
}