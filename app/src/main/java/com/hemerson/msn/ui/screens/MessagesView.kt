package com.hemerson.msn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hemerson.msn.ui.components.ItemChatList
import com.hemerson.msn.ui.components.MsnToolbar
import com.hemerson.msn.ui.theme.StatusBusy
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.SimpleWhite
import com.hemerson.msn.ui.theme.StatusOnline

@Composable
fun MessagesView(){
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(SimpleWhite)
    Column(
        Modifier
            .fillMaxSize()
            .background(SimpleWhite)) {
        MsnToolbar(
            userStatusColor = StatusBusy,
            isChatScreen = false
        )

            Row(
                modifier = Modifier.padding(
                    start = 32.dp,
                    top = 32.dp,
                    bottom = 16.dp
                ).clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "E-mail",
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )
                Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = null)
            }

        LazyColumn {
            item { ItemChatList(userStatusColor = StatusBusy, hasNewMessage = true)}
            item { ItemChatList(userStatusColor = StatusOnline, hasNewMessage = false)}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageViewPreview(){
    MessagesView()
}