package com.ncccdms.todolistbagian3.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ncccdms.todolistbagian3.nav.Screen.*
import com.ncccdms.todolistbagian3.ui.screen.menu.MenuScreen
import com.ncccdms.todolistbagian3.ui.screen.sign_in.SignInScreen
import com.ncccdms.todolistbagian3.ui.screen.sign_up.SignUpScreen
import com.ncccdms.todolistbagian3.ui.screen.splash.SplashScreen

@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = SplashScreen.route) {
            SplashScreen {
                navController.navigate(MenuScreen.route) {
                    popUpTo(SplashScreen.route) { inclusive = true }
                }
            }
        }

        composable(route = MenuScreen.route) {
            MenuScreen(
                navToSignInScreen = {
                    navController.navigate(SignInScreen.route)
                },
                navToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }

        composable(route = SignInScreen.route) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }

        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateToSignInScreen = {
                    navController.navigate(SignInScreen.route)
                }
            )
        }
    }
}