package com.ncccdms.todolistbagian3.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ncccdms.todolistbagian3.nav.Screen.*
import com.ncccdms.todolistbagian3.ui.screen.menu.MenuScreen
import com.ncccdms.todolistbagian3.ui.screen.sign_in.SignInScreen

@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MenuScreen.route
    ) {
        composable(
            route = MenuScreen.route
        ) {
            MenuScreen(
                navToSignInScreen = {
                    navController.navigate(SignInScreen.route)
                },
                navToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }

        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }
    }
}