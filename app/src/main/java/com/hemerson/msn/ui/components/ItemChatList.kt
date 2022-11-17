package com.hemerson.msn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.R
import com.hemerson.msn.ui.theme.Attention
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.Blue
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.theme.LightBlue
import com.hemerson.msn.ui.theme.SimpleWhite
import com.hemerson.msn.ui.theme.VeryLightBlue

@Composable
fun ItemChatList(
    isChatScreen: Boolean = false,
    hasNewMessage: Boolean = false,
    userStatusColor: Color
) {
    val profileStartPadding = if (isChatScreen) 24.dp else 8.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = if (hasNewMessage) LightBlue else SimpleWhite
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

            if (isChatScreen) {
                IconButton(onClick = { }) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(VeryLightBlue),
                        contentAlignment = Alignment.Center
                    ) {
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
                        .size(48.dp)
                        .clip(CircleShape)
                )
                MsnUserStatus(userStatusColor, isToolbar = false)
            }

            Column(
                Modifier
                    .sizeIn(0.dp, 56.dp)
                    .padding(start = 16.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Lia :)",
                    color = Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.sp,
                    maxLines = 1
                )
                Text(
                    text = "Chama ai",
                    color = Black.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    maxLines = 1
                )
            }

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .width(31.dp)
                    .height(24.dp)
                    .background(Blue, RoundedCornerShape(100)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "8",
                    color = SimpleWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    maxLines = 1
                )
            }

            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Attention),
                contentAlignment = Alignment.Center,
            ) {
                Image(painter = painterResource(id = R.drawable.ic_attention) , contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemChatListPreview() {
    ItemChatList(userStatusColor = Attention, isChatScreen = false)
}