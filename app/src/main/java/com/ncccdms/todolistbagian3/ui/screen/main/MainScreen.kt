package com.ncccdms.todolistbagian3.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ncccdms.todolistbagian3.core.Utils.Companion.sortTasksByDeadline
import com.ncccdms.todolistbagian3.data.dummy.TaskData
import com.ncccdms.todolistbagian3.ui.screen.main.components.NearlyDeadlineSection
import com.ncccdms.todolistbagian3.ui.screen.main.components.NextDeadlineSection

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    // Create a scroll state
    val scrollState = rememberScrollState()
    val sortedTasks = remember { sortTasksByDeadline(TaskData.dummytask) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Enable vertical scrolling
    ) {
        NearlyDeadlineSection(sortedTasks.take(1), navigateToDetail = navigateToDetail) // Display the first task as "Nearly Deadline"
        NextDeadlineSection(sortedTasks.drop(1).take(3), navigateToDetail = navigateToDetail) // Display the next three tasks as "Next Deadline"
    }
}





