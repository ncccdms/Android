package com.ncccdms.todolistbagian3.ui.screen.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack

@Composable
fun NearlyDeadlineSection(tasks: List<Task>, navigateToDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = "Nearly Deadline",
            color = BlueDark40,
            fontFamily = poppBlack,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Display the task(s) in this section
        tasks.forEach { task ->
            TaskCardNearlyDeadline(
                task = task,
                navigateToDetail = navigateToDetail
            )
        }
    }
}