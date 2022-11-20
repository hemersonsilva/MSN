package com.hemerson.msn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.Blue

@Composable
fun ItemToMessage(
    message: String
){
    Row(
        Modifier
            .widthIn(0.dp, 240.dp)
            .wrapContentHeight()
            .background(
                Blue,
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp
                )
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = message,
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 22.sp,
            textAlign = TextAlign.End
        )
    }
}

@Composable
@Preview
fun ItemToMessagePreview() {
    ItemToMessage("I like the music you are listening")
}