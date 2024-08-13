package com.ncccdms.todolistbagian3.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun TaskCardNextDeadline(title: String, deadline: String, isFinished: Boolean) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)) // Apply rounded corners to the entire card
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = deadline,
                fontFamily = poppBlack,
                fontSize = 16.sp,
                color = BlueDark40
            )
            Text(
                text = title,
                fontFamily = poppBold,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (isFinished) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .clickable {
                        // Handle click event
                    }
                    .padding(horizontal = 8.dp, vertical = 38.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Finish",
                    color = Color.White,
                    fontFamily = poppBlack,
                    modifier = Modifier.rotate(90f)
                )
            }
        }
    }
}