package com.ncccdms.todolistbagian3.ui.detail_list

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
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun DetailScreen(
    listId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DetailMain()
}

@Composable
fun DetailMain(){
    Column(modifier = Modifier.padding(16.dp)) {
        TextDetail(text = "Description Schedule", font = poppBlack, size = 24.sp)
        Spacer(modifier = Modifier.height(18.dp))
        TextDetail(text = "Membuat surat undangan rapat tim keamanan informasi (evaluasi penyelenggaraan bimtek dan persiapan kegiatan jasa konsultansi dengan PT. PITSI", font = poppBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Creator", font = poppBold, size = 16.sp)
        TextDetail(text = "Kabid 3", font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Create at", font = poppBold, size = 16.sp)
        TextDetail(text = "Friday, 27 May 2024", font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Deadline at", font = poppBold, size = 16.sp)
        TextDetail(text = "Friday, 3 June 2024", font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "PIC", font = poppBold, size = 16.sp)
        TextDetail(text = "Ghany", font = poppSemiBold, size = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextDetail(text = "Status", font = poppSemiBold, size = 16.sp)
        TextDetail(text = "Proses Koordinasi dengan Pak Endro (PT. Pratama Mukti Consultant)", font = poppSemiBold, size = 16.sp)
    }
}

@Composable
fun TextDetail(text: String, font: FontFamily, size: TextUnit) {
    Text(
        fontFamily = font,
        text = text,
        fontSize = size,
        color = BlueDark40
    )
}