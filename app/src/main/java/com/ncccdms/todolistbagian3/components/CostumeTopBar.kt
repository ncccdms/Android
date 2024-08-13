package com.ncccdms.todolistbagian3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.core.Constant.REVOKE_ACCESS_ITEM
import com.ncccdms.todolistbagian3.core.Constant.SIGN_OUT_ITEM
import com.ncccdms.todolistbagian3.ui.screen.home.components.TopProgressbar
import com.ncccdms.todolistbagian3.ui.theme.Blue40
import com.ncccdms.todolistbagian3.ui.theme.BlueDark40
import com.ncccdms.todolistbagian3.ui.theme.LightBlue40
import com.ncccdms.todolistbagian3.ui.theme.TintBlue40
import com.ncccdms.todolistbagian3.ui.theme.poppBold
import com.ncccdms.todolistbagian3.ui.theme.poppSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopBar(
    title: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
    progress: Float // New parameter for progress
) {
    var openMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Blue40)
    ) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontFamily = poppSemiBold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                openMenu = !openMenu
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.sett),
                                tint = Color.White,
                                contentDescription = null,
                            )
                        }
                    }
                }
            },
            actions = {
                DropdownMenu(
                    expanded = openMenu,
                    onDismissRequest = {
                        openMenu = !openMenu
                    }
                ) {
                    DropdownMenuItem(
                        text = { Text(SIGN_OUT_ITEM) },
                        onClick = {
                            signOut()
                            openMenu = !openMenu
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(REVOKE_ACCESS_ITEM) },
                        onClick = {
                            revokeAccess()
                            openMenu = !openMenu
                        }
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Blue40),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(100.dp))
        ) {
            TopProgressbar(
                progress = progress, // Use the computed progress here
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), // Padding horizontal
                color1 = LightBlue40,
                color2 = TintBlue40,
                trackColor = Color.White,
                textColor = BlueDark40,
                cornerRadius = 8.dp, // Adjust as needed
                fontFamily = poppBold,
                fontSize = 20.sp
            )
        }
    }
}
