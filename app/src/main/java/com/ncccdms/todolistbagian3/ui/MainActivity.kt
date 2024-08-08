package com.ncccdms.todolistbagian3.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ncccdms.todolistbagian3.components.LockScreenOrientation
import com.ncccdms.todolistbagian3.core.Utils.Companion.getIsSplashScreenShown
import com.ncccdms.todolistbagian3.nav.NavGraph
import com.ncccdms.todolistbagian3.nav.Screen.MenuScreen
import com.ncccdms.todolistbagian3.nav.Screen.ProfileScreen
import com.ncccdms.todolistbagian3.nav.Screen.SplashScreen
import com.ncccdms.todolistbagian3.nav.Screen.VerifyEmailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            navController = rememberNavController()

            val isSplashScreenShown = remember { getIsSplashScreenShown(this) }
            val startDestination = if (isSplashScreenShown) MenuScreen.route else SplashScreen.route

            NavGraph(
                navController = navController,
                startDestination = startDestination
            )

            AuthState()
        }
    }

    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        if (isUserSignedOut) {
            NavigateToMenuScreen()
        } else {
            if (viewModel.isEmailVerified) {
                NavigateToProfileScreen()
            } else {
                NavigateToVerifyEmailScreen()
            }
        }
    }

    @Composable
    private fun NavigateToMenuScreen() = navController.navigate(SplashScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToProfileScreen() = navController.navigate(ProfileScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToVerifyEmailScreen() = navController.navigate(VerifyEmailScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

}