package com.ncccdms.todolistbagian3.ui.screen.task.list_task

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.components.SectionTitle
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.components.TaskItem
import com.ncccdms.todolistbagian3.ui.theme.Blue40

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateToAddTask: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddTask, containerColor = Blue40) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp, 4.dp, 16.dp, 16.dp)
            ) {
                // Ongoing Tasks Section
                item {
                    SectionTitle(
                        title = "Ongoing Schedule",
                        icon = if (viewModel.ongoingExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                        onIconClick = { viewModel.toggleOngoingExpanded() }
                    )
                }
                if (viewModel.ongoingExpanded) {
                    items(viewModel.ongoingTasks) { task ->
                        TaskItem(task = task, navigateToDetail = navigateToDetail)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Deadline Tasks Section
                item {
                    SectionTitle(
                        title = "Deadline Schedule",
                        icon = if (viewModel.deadlineExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                        onIconClick = { viewModel.toggleDeadlineExpanded() }
                    )
                }
                if (viewModel.deadlineExpanded) {
                    items(viewModel.deadlineTasks) { task ->
                        TaskItem(task = task, navigateToDetail = navigateToDetail)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Finished Tasks Section
                item {
                    SectionTitle(
                        title = "Finished Schedule",
                        icon = if (viewModel.finishedExpanded) R.drawable.ic_filter else R.drawable.ic_filter,
                        onIconClick = { viewModel.toggleFinishedExpanded() }
                    )
                }
                if (viewModel.finishedExpanded) {
                    items(viewModel.finishedTasks) { task ->
                        TaskItem(task = task, navigateToDetail = navigateToDetail)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )
}
