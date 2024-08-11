package com.ncccdms.todolistbagian3.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    // Create a scroll state
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Enable vertical scrolling
    ) {
        NearlyDeadlineSection()
        NextDeadlineSection()
    }
}

@Composable
fun NearlyDeadlineSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = "Nearly Deadline",
            color = colorResource(id = R.color.blue_600),
            fontFamily = poppBlack,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TaskCardNearlyDeadline(
            title = "Membuat surat undangan sosialisasi metadata statistik",
            deadline = "Today",
            status = "Proses Koordinasi dengan Mas Adho (PT. PITSI)",
            isFinished = true
        )
    }
}

@Composable
fun TaskCardNearlyDeadline(title: String, deadline: String, status: String, isFinished: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)) // Optional: for rounded corners
    ) {
        // Title section with red background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontFamily = poppBlack,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        // Deadline and Status section with white background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Deadline: $deadline",
                    fontFamily = poppBlack,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.blue_600)
                )
                Text(
                    text = "Status: $status",
                    color = Color.Gray,
                    fontFamily = poppBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        if (isFinished) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .clickable {
                        // Action to perform on click
                    }
                    .padding(16.dp), // Padding after clickable to ensure full area is clickable
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Finish",
                    color = Color.White,
                    fontFamily = poppBlack
                )
            }
        }

    }
}

@Composable
fun NextDeadlineSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Next Deadline",
            color = colorResource(id = R.color.blue_600),
            fontFamily = poppBlack,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TaskCardNextDeadline(
            title = "Membuat surat undangan sosialisasi metadata statistik",
            deadline = "Today",
            isFinished = true
        )
        TaskCardNextDeadline(
            title = "Membuat surat undangan sosialisasi metadata statistik",
            deadline = "Today",
            isFinished = true
        )
        TaskCardNextDeadline(
            title = "Membuat surat undangan sosialisasi metadata statistik",
            deadline = "Today",
            isFinished = true
        )
    }
}

@Composable
fun TaskCardNextDeadline(title: String, deadline: String, isFinished: Boolean) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)) // Apply rounded corners to the entire card
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = deadline,
                fontFamily = poppBlack,
                fontSize = 16.sp,
                color = colorResource(id = R.color.blue_600)
            )
            Text(
                text = title,
                fontFamily = poppBold,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (isFinished) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .clickable {
                        // Handle click event
                    }
                    .padding(horizontal = 8.dp, vertical = 38.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Finish",
                    color = Color.White,
                    fontFamily = poppBlack,
                    modifier = Modifier.rotate(90f)
                )
            }
        }
    }
}
