package com.ncccdms.todolistbagian3.ui.screen.verify_email

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Constant.EMAIL_NOT_VERIFIED_MESSAGE
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.home.HomeViewModel
import com.ncccdms.todolistbagian3.ui.screen.home.components.RevokeAccess
import com.ncccdms.todolistbagian3.ui.screen.verify_email.components.ReloadUser
import com.ncccdms.todolistbagian3.ui.screen.verify_email.components.VerifyEmailContent

@Composable
fun VerifyEmailScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToHomeScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )

    RevokeAccess(
        snackbarHost = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )

}