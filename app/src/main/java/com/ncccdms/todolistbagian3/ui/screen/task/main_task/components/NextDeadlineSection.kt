package com.ncccdms.todolistbagian3.ui.screen.task.main_task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack

@Composable
fun NextDeadlineSection(tasks: List<Task>, navigateToDetail: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Next Deadline",
            color = BlueDark40,
            fontFamily = poppBlack,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Display the tasks in this section
        tasks.forEach { task ->
            TaskCardNextDeadline(
                task = task,
                navigateToDetail = navigateToDetail
            )
        }
    }
}