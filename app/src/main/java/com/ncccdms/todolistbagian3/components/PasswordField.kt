package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.ncccdms.todolistbagian3.core.Constant.PASSWORD_LABEL
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40


@Composable
fun PasswordField(
    password: TextFieldValue,
    onPasswordValueChange: (newValue: TextFieldValue) -> Unit
) {
    var passwordIsVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue ->
            onPasswordValueChange(newValue)
        },
        label = {
            Text(
                text = PASSWORD_LABEL
            )
        },
        colors = TextFieldDefaults.colors(BlueDark40),
        singleLine = true,
        visualTransformation = if (passwordIsVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
            val icon = if (passwordIsVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = {
                    passwordIsVisible = !passwordIsVisible
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    )
}