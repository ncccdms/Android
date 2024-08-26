package com.ncccdms.todolistbagian3.ui.screen.task.status_task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatusContent(
    padding : PaddingValues
){
    Column(
        modifier = Modifier.padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            item{
                Spacer(modifier = Modifier.height(16.dp))
                StatusItem()
                Spacer(modifier = Modifier.height(16.dp))
                StatusItem()
                Spacer(modifier = Modifier.height(16.dp))
                StatusItem()
                Spacer(modifier = Modifier.height(16.dp))
                StatusItem()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}