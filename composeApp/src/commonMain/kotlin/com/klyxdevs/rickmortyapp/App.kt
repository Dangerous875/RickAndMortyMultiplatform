package com.klyxdevs.rickmortyapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.klyxdevs.rickmortyapp.data.HomeScreenRoute
import com.klyxdevs.rickmortyapp.data.TestScreenRoute
import com.klyxdevs.rickmortyapp.ui.HomeScreen
import com.klyxdevs.rickmortyapp.ui.TestScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeScreenRoute) {
            composable<HomeScreenRoute> { HomeScreen(navController) }
            composable<TestScreenRoute> {
                val safeArgs = it.toRoute<TestScreenRoute>()
                TestScreen(safeArgs.string,navController)
            }
        }
    }
}