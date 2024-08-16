package com.ncccdms.todolistbagian3.ui.screen.forgot_password.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.components.EmailField
import com.ncccdms.todolistbagian3.components.SmallSpacer
import com.ncccdms.todolistbagian3.core.Constant.EMPTY_STRING
import com.ncccdms.todolistbagian3.core.Constant.RESET_PASSWORD_BUTTON
import com.ncccdms.todolistbagian3.core.Utils.Companion.validateEmail
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun ForgotPasswordContent(
    padding: PaddingValues,
    sendPasswordResetEmail: (email: String) -> Unit,
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
    var emailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }

    fun handleChangePassword() {
        emailError = !validateEmail(email.text)
        emailErrorMessage = if (emailError) "Email harus mengandung simbol @" else ""

        if (!emailError) {
            sendPasswordResetEmail(email.text)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
                if (emailError) {
                    emailError = false
                    emailErrorMessage = ""
                }
            },
            isError = emailError

        )
        SmallSpacer()
        Button(
            onClick = {
                handleChangePassword()
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_200)),
            shape = RoundedCornerShape(8.dp), // Sudut melengkung,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = RESET_PASSWORD_BUTTON,
                fontSize = 15.sp,
                fontFamily = poppSemiBold
            )
        }
    }
}