package com.ncccdms.todolistbagian3.ui.screen.task.main_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun TaskCardNearlyDeadline(task: Task, navigateToDetail: (Int) -> Unit) {
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { navigateToDetail(task.id) }
    ) {
        // Title section with red background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp)
        ) {
            Text(
                text = task.title,
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
                val dl = task.deadline
                val status = task.statusDesc
                Text(
                    text = "Deadline: $dl",
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
                Text(
                    text = "Remaining days: $remainingDays Days",
                    color = Color.Gray,
                    fontFamily = poppBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        if (task.taskStatus == TaskStatus.Finished) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .clickable { navigateToDetail(task.id) } // Handle the click action for finished tasks
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
