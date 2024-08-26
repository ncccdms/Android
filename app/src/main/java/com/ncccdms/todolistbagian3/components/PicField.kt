package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.domain.model.User
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppMedium
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String = "",
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    users: List<User>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { isExpanded -> onExpandedChange(isExpanded) }
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                readOnly = true,
                label = { Text(label, fontFamily = poppSemiBold) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedPlaceholderColor = BlueDark40,
                    unfocusedPlaceholderColor = Blue40,
                    focusedLabelColor = BlueDark40,
                    unfocusedLabelColor = Blue40,
                    containerColor = Color.White,
                    focusedIndicatorColor = BlueDark40,
                    unfocusedIndicatorColor = Blue40
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                users.forEach { user ->
                    DropdownMenuItem(
                        text = { Text(user.username, fontFamily = poppMedium) },
                        onClick = {
                            onValueChange(user.username)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                fontFamily = poppMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
