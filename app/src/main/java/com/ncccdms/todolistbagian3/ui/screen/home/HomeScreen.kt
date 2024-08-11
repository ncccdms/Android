package com.ncccdms.todolistbagian3.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.nav.NavigationItem
import com.ncccdms.todolistbagian3.nav.Screen.DetailListScreen
import com.ncccdms.todolistbagian3.nav.Screen.MainScreen
import com.ncccdms.todolistbagian3.nav.Screen.ListScreen
import com.ncccdms.todolistbagian3.ui.detail_list.DetailScreen
import com.ncccdms.todolistbagian3.ui.screen.list.ListScreen
import com.ncccdms.todolistbagian3.ui.screen.main.MainScreen
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.LightBlue40
import com.ncccdms.todolistbagian3.ui.theme.TintBlue40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        topBar = {
            TopHeader()
        },
        bottomBar = {
            BottomNavigationBar(navController)
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
                route = DetailListScreen.route,
                arguments = listOf(
                    navArgument("listId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getInt("listId") ?: -1
                DetailScreen(
                    listId = listId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun RoundedProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color1: Color = Color.Cyan,
    color2: Color = Color.Blue,
    trackColor: Color = Color.Gray,
    cornerRadius: Dp = 8.dp,
    stripeWidth: Dp = 10.dp, // Width of the alternating color stripes
    textColor: Color = Color.Black,
    fontFamily: FontFamily,
    fontSize: TextUnit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius)) // Clip the container to have rounded corners
            .background(trackColor) // Background for the track
    ) {
        // Draw alternating color stripes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(cornerRadius))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stripePaint1 = Paint().apply { color = color1 }
                val stripePaint2 = Paint().apply { color = color2 }
                val stripeWidthPx = stripeWidth.toPx()
                val progressWidth = size.width * progress
                var currentX = 0f
                var paintToUse = stripePaint1

                while (currentX < progressWidth) {
                    drawRect(
                        color = paintToUse.color,
                        topLeft = Offset(currentX, 0f),
                        size = Size(minOf(stripeWidthPx, progressWidth - currentX), size.height)
                    )
                    currentX += stripeWidthPx
                    paintToUse = if (paintToUse == stripePaint1) stripePaint2 else stripePaint1
                }
            }
        }

        // Add percentage text
        Text(
            text = "${(progress * 100).toInt()}%",
            color = textColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
}



@Composable
fun TopHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.blue_200))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.blue_200))
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.title_home),
                fontFamily = poppBlack,
                color = Color.White,
                fontSize = 20.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.sett),
                contentDescription = "Settings",
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp) // Padding horizontal
        ) {
            RoundedProgressIndicator(
                progress = 0.73f, // Example progress value
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), // Padding horizontal
                color1 = LightBlue40,
                color2 = TintBlue40,
                trackColor = Color.White,
                textColor = BlueDark40,
                cornerRadius = 8.dp, // Adjust as needed
                fontFamily = poppBlack,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
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
                        tint = colorResource(id = R.color.blue_200)
                    )
                },
                label = { Text(item.title, color = colorResource(id = R.color.blue_200)) },
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