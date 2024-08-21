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
import com.ncccdms.todolistbagian3.core.alarm.AlarmScheduler
import com.ncccdms.todolistbagian3.core.alarm.NotificationHelper
import com.ncccdms.todolistbagian3.core.alarm.PermissionHelper
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.nav.NavGraph
import com.ncccdms.todolistbagian3.nav.Screen
import com.ncccdms.todolistbagian3.nav.Screen.SplashScreen
import com.ncccdms.todolistbagian3.nav.Screen.VerifyEmailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()
    private fun scheduleAllTaskDeadlines(sortedTasks: List<Task>) {
        val alarmScheduler = AlarmScheduler(applicationContext)
        alarmScheduler.scheduleDailyTaskDeadlineNotification(sortedTasks)
    }
    private fun getSortedTasks(): List<Task> {
        // Fetch and return the list of sorted tasks with their statuses
        return listOf() // Example placeholder, replace with actual implementation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sortedTasks = getSortedTasks() // Implement this function to get your sorted tasks
        scheduleAllTaskDeadlines(sortedTasks)
        NotificationHelper(this).createNotificationChannel()
        PermissionHelper(this).requestNotificationPermission()

        setContent {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            navController = rememberNavController()

            NavGraph(
                navController = navController,
                startDestination = SplashScreen.route
            )

            AuthState()
        }
    }

    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        val isSplashScreenShown = remember { getIsSplashScreenShown(this@MainActivity) }

        if (!isSplashScreenShown) {
            // Show the splash screen if it hasn't been shown
            NavigateToSplashScreen()
        } else {
            // Handle the navigation based on auth state
            if (isUserSignedOut) {
                NavigateToSplashScreen()
            } else {
                if (viewModel.isEmailVerified) {
                    NavigateToHomeScreen()
                } else {
                    NavigateToVerifyEmailScreen()
                }
            }
        }
    }

    @Composable
    private fun NavigateToSplashScreen() = navController.navigate(SplashScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToHomeScreen() = navController.navigate(Screen.HomeScreen.route) {
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