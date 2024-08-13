package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ncccdms.todolistbagian3.core.Constant.EMAIL_LABEL
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppMedium
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    email: TextFieldValue,
    onEmailValueChange: (newValue: TextFieldValue) -> Unit,
    isError: Boolean,
) {
    val focusRequester = FocusRequester()

    OutlinedTextField(
        value = email,
        isError = isError,
        onValueChange = { newValue ->
            onEmailValueChange(newValue)

        },
        label = {
            Text(
                text = EMAIL_LABEL,
                fontFamily = poppMedium,
                color = if (isError) MaterialTheme.colorScheme.error else BlueDark40
            )
        },
        placeholder = {
            Text(
                text = "Enter your email",
                color = BlueDark40
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedPlaceholderColor = BlueDark40,
            unfocusedPlaceholderColor = Blue40,
            focusedLabelColor = BlueDark40,
            unfocusedLabelColor = Blue40,
            containerColor = Color.White, // Change this to your desired background color
            focusedBorderColor = BlueDark40,
            unfocusedBorderColor = Blue40
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)// Adjust background color as needed
        ,
    )

    LaunchedEffect(Unit) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }
}

