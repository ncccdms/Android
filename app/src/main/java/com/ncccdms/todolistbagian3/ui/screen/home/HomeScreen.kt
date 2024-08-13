package com.ncccdms.todolistbagian3.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.components.CostumeTopBar
import com.ncccdms.todolistbagian3.nav.Screen.DetailListScreen
import com.ncccdms.todolistbagian3.nav.Screen.ListScreen
import com.ncccdms.todolistbagian3.nav.Screen.MainScreen
import com.ncccdms.todolistbagian3.ui.detail_list.DetailScreen
import com.ncccdms.todolistbagian3.ui.screen.home.components.BottomNavBar
import com.ncccdms.todolistbagian3.ui.screen.list.ListScreen
import com.ncccdms.todolistbagian3.ui.screen.main.MainScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {

    val progress by remember { mutableStateOf(viewModel.progress) }

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
                progress = progress
            )
        },
        bottomBar = {
            BottomNavBar(navController)
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
                    }
                )
            }
            composable(
                route = "detail/{listId}", // The route pattern includes the argument
                arguments = listOf(
                    navArgument("listId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getInt("listId") ?: -1
                DetailScreen(
                    listId = listId,
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}
