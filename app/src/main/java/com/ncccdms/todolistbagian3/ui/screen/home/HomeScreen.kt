package com.ncccdms.todolistbagian3.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.components.CostumeTopBar
import com.ncccdms.todolistbagian3.nav.Screen.AddScreen
import com.ncccdms.todolistbagian3.nav.Screen.DetailListScreen
import com.ncccdms.todolistbagian3.nav.Screen.ListScreen
import com.ncccdms.todolistbagian3.nav.Screen.MainScreen
import com.ncccdms.todolistbagian3.ui.screen.task.detail_list.DetailScreen
import com.ncccdms.todolistbagian3.ui.screen.home.components.BottomNavBar
import com.ncccdms.todolistbagian3.ui.screen.task.add_task.AddScreen
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.ListScreen
import com.ncccdms.todolistbagian3.ui.screen.task.main_task.MainScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val username by viewModel.username.collectAsState()
    val progress by remember { mutableStateOf(viewModel.progress) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            CostumeTopBar(
                title = stringResource(id = R.string.title_home),
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                },
                username = username,
                progress = progress
            )
        },
        bottomBar = {
            // Show BottomNavBar only on MainScreen or ListScreen
            if (currentRoute == MainScreen.route || currentRoute == ListScreen.route) {
                BottomNavBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainScreen.route) {
                MainScreen(
                    navigateToDetail = { listId ->
                        navController.navigate(DetailListScreen.createRoute(listId))
                    }
                )
            }
            composable(ListScreen.route) {
                ListScreen(
                    navigateToDetail = { listId ->
                        navController.navigate(DetailListScreen.createRoute(listId))
                    },
                    navigateToAddTask = { navController.navigate(AddScreen.route) }
                )
            }
            composable(route = AddScreen.route) {
                AddScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(
                route = "detail/{listId}",
                arguments = listOf(
                    navArgument("listId") { type = NavType.StringType    }
                )
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId") ?: ""
                DetailScreen(
                    listId = listId,
                    navigateBack = { navController.navigateUp() }
                )
            }

        }
    }
}

