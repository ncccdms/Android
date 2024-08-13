package com.ncccdms.todolistbagian3.ui.screen.sign_up.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.components.EmailField
import com.ncccdms.todolistbagian3.components.PasswordField
import com.ncccdms.todolistbagian3.core.Constant.ALREADY_USER
import com.ncccdms.todolistbagian3.core.Constant.EMPTY_STRING
import com.ncccdms.todolistbagian3.core.Constant.SIGN_UP_BUTTON
import com.ncccdms.todolistbagian3.core.Utils.Companion.validateEmail
import com.ncccdms.todolistbagian3.core.Utils.Companion.validatePassword
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.Gray10
import com.ncccdms.todolistbagian3.ui.theme.poppMedium
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    padding: PaddingValues,
    signUp: (email: String, password: String) -> Unit,
    navigateToSignInScreen: () -> Unit
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    fun handleLogin() {
        emailError = !validateEmail(email.text)
        emailErrorMessage = if (emailError) "Email harus mengandung simbol @" else ""

        passwordError = !validatePassword(password.text)
        passwordErrorMessage = if (passwordError) "Password harus memiliki 8 karakter, 1 huruf besar dan simbol" else ""

        if (!emailError && !passwordError) {
            signUp(email.text, password.text)
        }
    }

    val keyboard = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Gray10),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_cilacap), // Your logo resource
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.title_diskominfo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(100.dp))
            EmailField(
                email = email,
                isError = emailError,
                onEmailValueChange = { newValue ->
                    email = newValue
                    if (emailError) {
                        emailError = false
                        emailErrorMessage = ""
                    }
                }
            )
            if (emailError) {
                Text(
                    text = emailErrorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            PasswordField(
                password = password,
                isError = passwordError,
                onPasswordValueChange = { newValue ->
                    password = newValue
                    if (passwordError) {
                        passwordError = false
                        passwordErrorMessage = ""
                    }
                }
            )
            if (passwordError) {
                Text(
                    text = passwordErrorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    keyboard?.hide()
                    handleLogin()
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_200)),
                shape = RoundedCornerShape(8.dp), // Sudut melengkung,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(SIGN_UP_BUTTON, fontSize = 16.sp, fontFamily = poppSemiBold)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.clickable {
                    navigateToSignInScreen()
                },
                fontFamily = poppMedium,
                text = ALREADY_USER,
                color = Blue40,
                fontSize = 13.sp
            )
        }
    }
}
