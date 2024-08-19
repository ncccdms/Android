package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String = "",
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = isError,
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedPlaceholderColor = BlueDark40,
                unfocusedPlaceholderColor = Blue40,
                focusedLabelColor = BlueDark40,
                unfocusedLabelColor = Blue40,
                containerColor = Color.White, // Change this to your desired background color
                focusedBorderColor = BlueDark40,
                unfocusedBorderColor = Blue40
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
