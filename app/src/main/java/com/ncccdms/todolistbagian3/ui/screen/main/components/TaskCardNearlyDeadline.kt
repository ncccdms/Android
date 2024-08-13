package com.ncccdms.todolistbagian3.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun TaskCardNearlyDeadline(title: String, deadline: String, status: String, isFinished: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)) // Optional: for rounded corners
    ) {
        // Title section with red background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontFamily = poppBlack,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        // Deadline and Status section with white background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Deadline: $deadline",
                    fontFamily = poppBlack,
                    fontSize = 18.sp,
                    color = BlueDark40
                )
                Text(
                    text = "Status: $status",
                    color = Color.Gray,
                    fontFamily = poppBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        if (isFinished) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .clickable {
                        // Action to perform on click
                    }
                    .padding(16.dp), // Padding after clickable to ensure full area is clickable
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Finish",
                    color = Color.White,
                    fontFamily = poppBlack
                )
            }
        }

    }
}