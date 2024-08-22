package com.ncccdms.todolistbagian3.ui.screen.task.add_task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.task.TaskViewModel

@Composable
fun AddScreen(
    viewModel: TaskViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            AddContent(
                padding = padding,
                addTask = { title, deadline, statusDesc, isFinished, pic ->
                    viewModel.addTask(
                        title = title,
                        deadline = deadline,
                        statusDesc = statusDesc,
                        isFinished = isFinished,
                        pic = pic
                    )
                }
            )
        }
    )

    AddTask(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}
