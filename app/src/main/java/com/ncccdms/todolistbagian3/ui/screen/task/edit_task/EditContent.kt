package com.ncccdms.todolistbagian3.ui.screen.task.edit_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.components.AddField
import com.ncccdms.todolistbagian3.components.DatePickerDocked
import com.ncccdms.todolistbagian3.components.PicField
import com.ncccdms.todolistbagian3.core.Constant.DESC_DATE_BLANK_LABEL
import com.ncccdms.todolistbagian3.core.Constant.DESC_PIC_BLANK_LABEL
import com.ncccdms.todolistbagian3.core.Constant.DESC_STATUS_BLANK_LABEL
import com.ncccdms.todolistbagian3.core.Constant.DESC_STATUS_LABEL
import com.ncccdms.todolistbagian3.core.Constant.DESC_TASK_BLANK_LABEL
import com.ncccdms.todolistbagian3.core.Constant.DESC_TASK_LABEL
import com.ncccdms.todolistbagian3.core.Constant.EDIT_BUTTON
import com.ncccdms.todolistbagian3.domain.model.Response
import com.ncccdms.todolistbagian3.domain.model.User
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun EditContent(
    padding: PaddingValues,
    editTask: (title: String, deadline: String, statusDesc: String, pic: String) -> Unit,
    allUsersResponse: Response<List<User>>,
    navigateBack: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var pic by remember { mutableStateOf("") }
    var statusDescription by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var isAddClicked by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    // State untuk pesan kesalahan
    var titleError by remember { mutableStateOf("") }
    var picError by remember { mutableStateOf("") }
    var statusDescriptionError by remember { mutableStateOf("") }
    var deadlineError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        AddField(
            value = title,
            onValueChange = { title = it },
            label = DESC_TASK_LABEL,
            isError = isAddClicked && titleError.isNotEmpty(),
            errorMessage = titleError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        when (allUsersResponse) {
            is Response.Success -> {
                val allUsers = allUsersResponse.data
                PicField(
                    value = pic,
                    onValueChange = { pic = it },
                    label = "PIC",
                    isError = isAddClicked && picError.isNotEmpty(),
                    errorMessage = picError,
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    users = allUsers
                )
            }
            is Response.Loading -> Response.Loading
            is Response.Failure -> Text("Error: ${allUsersResponse.e.message}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        AddField(
            value = statusDescription,
            onValueChange = { statusDescription = it },
            label = DESC_STATUS_LABEL,
            isError = isAddClicked && statusDescriptionError.isNotEmpty(),
            errorMessage = statusDescriptionError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DatePickerDocked(
            onDateSelected = { selectedDate ->
                deadline = selectedDate
            },
            isError = isAddClicked && deadlineError.isNotEmpty(),
            errorMessage = deadlineError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Reset error messages
                titleError = ""
                picError = ""
                statusDescriptionError = ""
                deadlineError = ""

                var isValid = true

                if (title.isEmpty()) {
                    titleError = DESC_TASK_BLANK_LABEL
                    isValid = false
                }
                if (pic.isEmpty()) {
                    picError = DESC_PIC_BLANK_LABEL
                    isValid = false
                }
                if (statusDescription.isEmpty()) {
                    statusDescriptionError = DESC_STATUS_BLANK_LABEL
                    isValid = false
                }
                if (deadline.isEmpty()) {
                    deadlineError = DESC_DATE_BLANK_LABEL
                    isValid = false
                }

                if (isValid) {
                    editTask(title, deadline, statusDescription, pic)
                    isAddClicked = true
                    navigateBack()
                } else {
                    isAddClicked = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Blue40),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(text = EDIT_BUTTON, fontFamily = poppSemiBold, fontSize = 20.sp)
        }
    }

}