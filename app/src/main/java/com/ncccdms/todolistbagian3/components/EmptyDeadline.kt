package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.core.Constant.EMPTY_DEADLINE
import com.ncccdms.todolistbagian3.core.Constant.TASK_FREE_DEADLINE
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun EmptyDeadline() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top, // Align content to the top
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp) // Adjust the top padding to control the distance from the top
    ) {
        Image(
            painter = painterResource(id = R.drawable.relax2), // Replace with your image resource
            contentDescription = EMPTY_DEADLINE,
            modifier = Modifier.size(200.dp) // Set the size of the image
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = TASK_FREE_DEADLINE,
            fontFamily = poppBold,
            fontSize = 18.sp,
            color = BlueDark40,
            textAlign = TextAlign.Center
        )
    }
}
