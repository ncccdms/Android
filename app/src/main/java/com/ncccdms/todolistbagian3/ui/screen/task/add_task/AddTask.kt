package com.ncccdms.todolistbagian3.ui.screen.task.add_task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.components.ProgressBar
import com.ncccdms.todolistbagian3.domain.model.Response.Failure
import com.ncccdms.todolistbagian3.domain.model.Response.Loading
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.ui.screen.sign_in.SignInViewModel

@Composable
fun AddTask(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when (val signInResponse = viewModel.signInResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}