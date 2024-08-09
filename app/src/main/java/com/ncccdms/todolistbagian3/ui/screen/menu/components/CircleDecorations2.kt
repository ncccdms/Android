package com.ncccdms.todolistbagian3.ui.screen.menu.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ncccdms.todolistbagian3.R

@Composable
fun CircleDecorations2() {
    val transition = rememberInfiniteTransition(label = "")
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val colorBlue200 = colorResource(id = R.color.blue_200)
    val colorBlue100 = colorResource(id = R.color.blue_100)

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = colorBlue200,
            radius = 130.dp.toPx() * scale,
            center = Offset(size.width - 350.dp.toPx(), 10.dp.toPx())
        )
        drawCircle(
            color = colorBlue100,
            radius = 80.dp.toPx() * scale,
            center = Offset(size.width - 380.dp.toPx(), 40.dp.toPx())
        )
    }
}