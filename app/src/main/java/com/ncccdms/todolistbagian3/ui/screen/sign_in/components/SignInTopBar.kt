package com.ncccdms.todolistbagian3.ui.screen.sign_in.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.components.ProgressBar
import com.ncccdms.todolistbagian3.core.Constant.SIGN_IN_SCREEN
import com.ncccdms.todolistbagian3.domain.model.Response.*
import com.ncccdms.todolistbagian3.core.Utils.Companion.print
import com.ncccdms.todolistbagian3.ui.screen.sign_in.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInTopBar() {
    TopAppBar (
        title = {
            Text(
                text = SIGN_IN_SCREEN
            )
        }
    )
}