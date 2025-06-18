package com.example.borutoapp.navigation

import WelcomeScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.screens.home.HomeScreen
import com.example.borutoapp.presentation.screens.welcome.WelcomeViewModel
import com.example.borutoapp.utils.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val welcomeViewModel: WelcomeViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(onFinishButtonClick = {
                welcomeViewModel.saveOnBoardingState(complete = true)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            })
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Search.route) {

        }
    }
}