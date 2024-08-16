package com.ncccdms.todolistbagian3.ui.screen.add_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun AddScreen() {
    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = "Hello World", fontFamily = poppBold, textAlign = TextAlign.Center)
    }
}