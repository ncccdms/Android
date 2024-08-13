package com.ncccdms.todolistbagian3.ui.screen.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TopProgressbar(
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