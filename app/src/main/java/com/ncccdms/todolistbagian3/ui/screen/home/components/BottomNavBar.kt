package com.ncccdms.todolistbagian3.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.nav.NavigationItem
import com.ncccdms.todolistbagian3.nav.Screen.ListScreen
import com.ncccdms.todolistbagian3.nav.Screen.MainScreen
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),

            icon = R.drawable.home,
            screen = MainScreen
        ),
        NavigationItem(
            title = stringResource(R.string.menu_list),
            icon = R.drawable.list,
            screen = ListScreen
        )
    )

    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .shadow(12.dp)
            .background(Color.White)
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = Blue40
                    )
                },
                label = { Text(item.title, color = BlueDark40) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}