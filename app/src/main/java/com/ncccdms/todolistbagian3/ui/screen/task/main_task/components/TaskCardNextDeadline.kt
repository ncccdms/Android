package com.ncccdms.todolistbagian3.ui.screen.task.main_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.core.Utils.Companion.calculateRemainingDays
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun TaskCardNextDeadline(task: Task, navigateToDetail: (Int) -> Unit) {
    var remainingDays by remember { mutableStateOf(0L) }

    // Calculate remaining days initially and update daily
    LaunchedEffect(Unit) {
        remainingDays = calculateRemainingDays(task.deadline)
        // Schedule an update every day at midnight
        while (true) {
            kotlinx.coroutines.delay(24 * 60 * 60 * 1000L) // Delay for 24 hours
            remainingDays = calculateRemainingDays(task.deadline)
        }
    }

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp)) // Apply shadow before clipping and background
            .clip(RoundedCornerShape(12.dp)) // Apply rounded corners to the entire card
            .background(Color.White)
            .clickable { navigateToDetail(task.id) } // Add the clickable action here
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "Remaining days: $remainingDays Days",
                fontFamily = poppBlack,
                fontSize = 16.sp,
                color = BlueDark40
            )
            Text(
                text = task.title,
                fontFamily = poppBold,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (task.taskStatus == TaskStatus.Finished) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
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
