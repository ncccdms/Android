package com.ncccdms.todolistbagian3.ui.screen.sign_up

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Constant.VERIFY_EMAIL_MESSAGE
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.ui.screen.menu.components.CircleDecorations
import com.ncccdms.todolistbagian3.ui.screen.sign_up.components.SendEmailVerification
import com.ncccdms.todolistbagian3.ui.screen.sign_up.components.SignUp
import com.ncccdms.todolistbagian3.ui.screen.sign_up.components.SignUpContent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignInScreen: () -> Unit
){
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = { email, password, username->
                    viewModel.signUpWithEmailAndPassword(email, password, username)
                },
                navigateToSignInScreen = navigateToSignInScreen
            )
            CircleDecorations()
        }
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        }
    )

    SendEmailVerification()
}