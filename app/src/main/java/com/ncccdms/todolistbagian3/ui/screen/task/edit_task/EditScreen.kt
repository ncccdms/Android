package com.ncccdms.todolistbagian3.ui.screen.task.edit_task

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.task.TaskViewModel
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.ListViewModel

@Composable
fun EditScreen(
    viewModel:TaskViewModel = hiltViewModel(),
    navigateBack: () -> Unit
){
    val context = LocalContext.current
    val allUsersResponse = viewModel.allUsersResponse

    Scaffold(
        content = { padding ->
            EditContent(
                padding = PaddingValues(
                    start = padding.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    end = padding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    top = padding.calculateTopPadding() + 16.dp,
                    bottom = padding.calculateBottomPadding() + 16.dp
                ),
                navigateBack = navigateBack,
                editTask = { title, deadline, statusDesc, pic ->
                   //view model update task
                },
                allUsersResponse = allUsersResponse
            )
        }
    )

    EditTask(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}