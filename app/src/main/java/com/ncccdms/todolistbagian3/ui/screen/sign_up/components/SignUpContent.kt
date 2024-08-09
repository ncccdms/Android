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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
                onEmailValueChange = { newValue ->
                    email = newValue
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(
                password = password,
                onPasswordValueChange = { newValue ->
                    password = newValue
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    keyboard?.hide()
                    signUp(email.text, password.text)
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_200)),
                shape = RoundedCornerShape(8.dp), // Sudut melengkung,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(SIGN_UP_BUTTON, fontSize = 16.sp, fontFamily = poppSemiBold)
            }
            Spacer(modifier = Modifier.height(16.dp))
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
