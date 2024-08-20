package com.ncccdms.todolistbagian3.ui.screen.task.list_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.core.Utils.Companion.showMessage
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun TaskItem(
    task: Task,
    navigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (task.taskStatus) {
                TaskStatus.Ongoing -> Blue40
                TaskStatus.Deadline -> Color.Red
                TaskStatus.Finished -> Color.Green
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(task.id) }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = if (task.isFinished) R.drawable.bx_checkbox_checked else R.drawable.bx_checkbox
                ),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { showMessage(context,"CheckBox") }
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .fillMaxHeight()
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = poppBold,
                        fontSize = 16.sp,
                        color = BlueDark40
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.deadline,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = poppSemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.labelMedium,
                        color = BlueDark40,
                        fontSize = 16.sp,
                        fontFamily = poppBold
                    )
                    Text(
                        text = task.taskStatus.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = BlueDark40,
                        fontFamily = poppSemiBold
                    )
                }
                IconButton(onClick = { showMessage(context,"Edit Task") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Edit Task",
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    }
}