package com.ncccdms.todolistbagian3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R

// Set of Material typography styles to start with
val poppBlack = FontFamily(
    Font(R.font.popp_black)
)

val poppBold = FontFamily(
    Font(R.font.popp_bold)
)

val poppMedium = FontFamily(
    Font(R.font.popp_medium)
)

val poppSemiBold = FontFamily(
    Font(R.font.popp_semi_bold)
)

val poppRegular = FontFamily(
    Font(R.font.popp_regular)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)