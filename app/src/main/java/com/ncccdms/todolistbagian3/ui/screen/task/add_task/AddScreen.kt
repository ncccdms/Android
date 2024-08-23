package com.ncccdms.todolistbagian3.ui.screen.task.add_task

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.task.TaskViewModel

@Composable
fun AddScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            AddContent(
                padding = PaddingValues(
                    start = padding.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    end = padding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    top = padding.calculateTopPadding() + 16.dp,
                    bottom = padding.calculateBottomPadding() + 16.dp
                ),
                addTask = { title, deadline, statusDesc, pic ->
                    viewModel.addTask(
                        title = title,
                        deadline = deadline,
                        statusDesc = statusDesc,
                        pic = pic,
                    )
                },
                navigateBack = navigateBack
            )
        }
    )

    AddTask(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}
