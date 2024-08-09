package com.ncccdms.todolistbagian3.ui.screen.menu

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.ui.screen.menu.components.CircleDecorations
import com.ncccdms.todolistbagian3.ui.screen.menu.components.CircleDecorations2
import com.ncccdms.todolistbagian3.ui.screen.menu.components.LogoSection
import com.ncccdms.todolistbagian3.ui.theme.poppBlack
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppMedium
import com.ncccdms.todolistbagian3.ui.theme.poppRegular
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@Composable
fun MenuScreen(
    navToSignInScreen: () -> Unit,
    navToSignUpScreen: () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize()) {
        CircleDecorations()
        CircleDecorations2()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoSection()
            Spacer(modifier = Modifier.height(45.dp))
            val colorBlue400 = colorResource(id = R.color.blue_400)
            Text(
                text = stringResource(id = R.string.title_getStarted),
                fontSize = 20.sp,
                fontFamily = poppSemiBold,
                color = colorBlue400,
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.lorem_ipsum),
                textAlign = TextAlign.Center,
                fontFamily = poppRegular,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = navToSignInScreen ,
                shape = RoundedCornerShape(8.dp), // Sudut melengkung,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_200)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .height(50.dp)
            ) {
                Text(text = "Sign In",fontSize = 18.sp,fontFamily = poppBold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = navToSignUpScreen,
                shape = RoundedCornerShape(8.dp), // Sudut melengkung
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_200)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .height(50.dp)
            ) {
                Text(text = "Sign Up",fontSize = 18.sp,fontFamily = poppBold)
            }
        }
    }
}