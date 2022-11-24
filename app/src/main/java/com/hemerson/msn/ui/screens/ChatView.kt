package com.hemerson.msn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hemerson.msn.R
import com.hemerson.msn.ui.components.ItemFromMessage
import com.hemerson.msn.ui.components.ItemToMessage
import com.hemerson.msn.ui.components.MsnTextField
import com.hemerson.msn.ui.components.MsnToolbar
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.theme.SimpleWhite
import com.hemerson.msn.ui.theme.StatusOnline

@Composable
fun ChatView() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(SimpleWhite)
    Column(
        Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.chat_background),
                contentScale = ContentScale.Crop
            )
    ) {
        MsnToolbar(
            userStatusColor = StatusOnline,
            isChatScreen = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 18.dp),
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = false,
            flingBehavior = ScrollableDefaults.flingBehavior(),
        ) {
            item { ItemFromMessage(message = "hahaha obviously") }
            item { ItemToMessage(message = "I like the music you are listening to") }
            item { ItemFromMessage(message = "my favorite song :)") }
            item { ItemToMessage(message = "my favorite is pandora of parkway drive. you know?") }
            item { ItemFromMessage(message = "this song is like:") }
        }
        Card(
            Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            elevation = 4.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    MsnTextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        value = "",
                        updatedValue = {},
                        placeholderText = "Digite a mensagem...",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Send
                    )
                    IconButton(
                        onClick = {

                        },
                        Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(100))
                            .background(DarkBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = null,
                            tint = SimpleWhite
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatViewPreview() {
    ChatView()
}