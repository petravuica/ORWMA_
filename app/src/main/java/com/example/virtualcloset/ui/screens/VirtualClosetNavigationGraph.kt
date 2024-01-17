package com.example.virtualcloset.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun VirtualClosetNavigationGraph(
    userInputViewModel: UserInputViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.WELCOME_SCREEN){
        composable(Routes.WELCOME_SCREEN){
            WelcomeScreen(navController)
        }
        composable(Routes.USER_INPUT_SCREEN){
            UserInputScreen(navController)
        }
        composable(Routes.ADD_CLOTHES_SCREEN){
            AddClothesScreen(UserInputViewModel())
        }
        composable(Routes.T_SHIRT){
            Tshirt(navController)
        }


    }
}