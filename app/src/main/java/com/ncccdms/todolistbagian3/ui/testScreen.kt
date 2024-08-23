package com.ncccdms.todolistbagian3.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.ui.screen.task.detail_list.TextDetail
import com.ncccdms.todolistbagian3.ui.theme.poppBlack

@Composable
fun test(){
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)){
        TextDetail(text = "Hellow World", font = poppBlack, size = 30.sp)
    }
}