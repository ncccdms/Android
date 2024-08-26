package com.ncccdms.todolistbagian3.ui.screen.task.status_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Preview(showBackground = true)
@Composable
fun StatusItem() {
    val bullet = "\u2022"
    val messages = listOf(
        "Hey This is first paragraph",
        "Hey this is my second paragraph. Any this is 2nd line.",
        "Hey this is 3rd paragraph."
    )
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

    Card(
        modifier = Modifier
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(White)
    ){
        Column(modifier = Modifier.padding(16.dp)){
            //make dynamic name
            Text(text = "Saddam", fontFamily = poppBold, fontSize = 24.sp, color = Blue40)
            Text(text = "Tugas: ", fontFamily = poppSemiBold,color = Blue40)
            Text(
                //make it dynamic bullet list
                buildAnnotatedString {
                    messages.forEach {
                        withStyle(style = paragraphStyle) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    }
                },
                color = Blue40, fontFamily = poppSemiBold
            )
        }
    }
}

