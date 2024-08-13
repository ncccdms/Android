package com.ncccdms.todolistbagian3.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.core.Utils.Companion.sortTasksByDeadline
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskData
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import com.ncccdms.todolistbagian3.ui.screen.main.components.NearlyDeadlineSection
import com.ncccdms.todolistbagian3.ui.screen.main.components.NextDeadlineSection
import com.ncccdms.todolistbagian3.ui.screen.main.components.TaskCardNearlyDeadline
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold

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
        NearlyDeadlineSection(sortedTasks.take(1)) // Display the first task as "Nearly Deadline"
        NextDeadlineSection(sortedTasks.drop(1).take(3)) // Display the next three tasks as "Next Deadline"
    }
}





