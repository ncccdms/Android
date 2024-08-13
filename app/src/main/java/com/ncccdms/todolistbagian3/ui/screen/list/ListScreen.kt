package com.ncccdms.todolistbagian3.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskData
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    var ongoingExpanded by remember { mutableStateOf(true) }
    var deadlineExpanded by remember { mutableStateOf(true) }
    var finishedExpanded by remember { mutableStateOf(true) }

    val ongoingTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Ongoing }
    val deadlineTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Deadline }
    val finishedTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Finished }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 4.dp, 16.dp, 16.dp)
    ) {
        // Ongoing Tasks Section
        item {
            SectionTitle(
                title = "Ongoing Schedule",
                icon = if (ongoingExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                onIconClick = { ongoingExpanded = !ongoingExpanded }
            )
        }
        if (ongoingExpanded) {
            items(ongoingTasks) { task ->
                TaskItem(task = task, navigateToDetail = navigateToDetail)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Deadline Tasks Section
        item {
            SectionTitle(
                title = "Deadline Schedule",
                icon = if (deadlineExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                onIconClick = { deadlineExpanded = !deadlineExpanded }
            )
        }
        if (deadlineExpanded) {
            items(deadlineTasks) { task ->
                TaskItem(task = task, navigateToDetail = navigateToDetail)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Finished Tasks Section
        item {
            SectionTitle(
                title = "Finished Schedule",
                icon = if (finishedExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                onIconClick = { finishedExpanded = !finishedExpanded }
            )
        }
        if (finishedExpanded) {
            items(finishedTasks) { task ->
                TaskItem(task = task, navigateToDetail = navigateToDetail)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SectionTitle(
    title: String,
    icon: Int,
    onIconClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = BlueDark40,
            fontFamily = poppBold,
            fontSize = 20.sp
        )
        IconButton(onClick = onIconClick) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = if (icon == R.drawable.ic_filter) "Collapse" else "Expand"
            )
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    navigateToDetail: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (task.taskStatus) {
                TaskStatus.Ongoing -> Blue40
                TaskStatus.Deadline -> Color.Red
                TaskStatus.Finished -> Color.Green
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(task.id) }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isFinished,
                onCheckedChange = { /* TODO: Handle checkbox state */ },
                colors = CheckboxDefaults.colors(
                    checkedColor = BlueDark40,
                    uncheckedColor = Color.White,
                    checkmarkColor = BlueDark40
                )
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .fillMaxHeight()
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = poppBold,
                        fontSize = 16.sp,
                        color = BlueDark40
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.deadline,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = poppSemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.labelMedium,
                        color = BlueDark40,
                        fontSize = 16.sp,
                        fontFamily = poppBold
                    )
                    Text(
                        text = task.taskStatus.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = BlueDark40,
                        fontFamily = poppSemiBold
                    )
                }
                IconButton(onClick = { /* TODO: Handle edit click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Edit Task",
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    }
}
