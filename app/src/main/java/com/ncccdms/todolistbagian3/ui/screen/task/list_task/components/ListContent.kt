package com.ncccdms.todolistbagian3.ui.screen.task.list_task.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.ui.screen.task.TaskViewModel

@Composable
fun ListContent(
    viewModel: TaskViewModel,
    paddingValues: PaddingValues,
    navigateToDetail : (String) -> Unit,
    navigateToEdit : (String) -> Unit,
    checkedTask: (String, Boolean) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(start = 16.dp, end = 16.dp)
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
                TaskItem(task = task, navigateToDetail = navigateToDetail, navigateToEdit = navigateToEdit, checkedTask = checkedTask)
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
                TaskItem(task = task, navigateToDetail = navigateToDetail, navigateToEdit = navigateToEdit, checkedTask = checkedTask)
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
                TaskItem(task = task, navigateToDetail = navigateToDetail, navigateToEdit = navigateToEdit, checkedTask = checkedTask)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}