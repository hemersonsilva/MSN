package com.hemerson.msn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.R
import com.hemerson.msn.ui.theme.StatusBusy
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.theme.VeryLightBlue

@Composable
fun MsnToolbar(
    isChatScreen: Boolean = false,
    userStatusColor: Color
) {
    val profileStartPadding = if (isChatScreen) 24.dp else 8.dp

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(
                    start = 8.dp,
                    top = 24.dp,
                    end = 0.dp,
                    bottom = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            
            if (isChatScreen){
                IconButton(onClick = {  }) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(VeryLightBlue),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = DarkBlue
                        )
                    }
                }
            }

            Box(
                Modifier
                    .padding(start = profileStartPadding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
                MsnUserStatus(userStatusColor)
            }

            Column(
                Modifier
                    .sizeIn(0.dp, 56.dp)
                    .padding(start = 16.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Emily Burton",
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    maxLines = 1
                )
                Text(
                    text = "il ballo della vita .-.",
                    color = Black.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    maxLines = 1
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = if (isChatScreen) Icons.Outlined.MoreVert else Icons.Outlined.Settings,
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MsnToolbarPreview() {
    MsnToolbar(userStatusColor = StatusBusy, isChatScreen = true)
}