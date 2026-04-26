package com.rodrigoguerrero.amro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.ui.feature.details.MovieDetailsScreen
import com.rodrigoguerrero.ui.feature.details.navigation.DetailsRoutes
import com.rodrigoguerrero.ui.home.HomeScreen
import com.rodrigoguerrero.ui.home.navigation.HomeRoutes

@Composable
internal fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRoutes.Home,
        ) {
            composable<HomeRoutes.Home> {
                HomeScreen(
                    onNavigateToDetails = { movieId ->
                        navController.navigate(DetailsRoutes.Details(id = movieId))
                    },
                )
            }
            composable<DetailsRoutes.Details> {
                MovieDetailsScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
