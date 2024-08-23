package com.ncccdms.todolistbagian3.ui.screen.task.detail_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun DetailContent(task: Task) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextDetail(text = "Description Schedule", font = poppBlack, size = 24.sp)
        Spacer(modifier = Modifier.height(18.dp))
        TextDetail(text = task.title, font = poppBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Creator", font = poppBold, size = 16.sp)
        TextDetail(text = task.creator, font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Created at", font = poppBold, size = 16.sp)
        TextDetail(text = task.createAt, font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Deadline", font = poppBold, size = 16.sp)
        TextDetail(text = task.deadline, font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "PIC", font = poppBold, size = 16.sp)
        TextDetail(text = task.pic, font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Status", font = poppBold, size = 16.sp)
        TextDetail(text = task.statusDesc, font = poppSemiBold, size = 16.sp)
    }
}

@Composable
fun TextDetail(text: String, font: FontFamily, size: TextUnit) {
    Text(
        text = text,
        fontFamily = font,
        fontSize = size,
        color = BlueDark40 // Replace with your desired text color
    )
}

