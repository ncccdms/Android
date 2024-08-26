package com.ncccdms.todolistbagian3.ui.screen.task.status_task

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.ListViewModel
import com.ncccdms.todolistbagian3.ui.screen.task.status_task.components.StatusContent
import com.ncccdms.todolistbagian3.ui.screen.task.status_task.components.StatusTask

@Composable
fun StatusScreen(
    viewModel: ListViewModel = hiltViewModel()
){
    val context = LocalContext.current
    Scaffold(
        content = { padding ->
            StatusContent(
                padding = PaddingValues(
                    start = padding.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    end = padding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
            )
        }

    )

    StatusTask(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}