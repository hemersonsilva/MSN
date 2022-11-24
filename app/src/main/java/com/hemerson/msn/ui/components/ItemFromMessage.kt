package com.hemerson.msn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.SimpleWhite

@Composable
fun ItemFromMessage(
    message: String
) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .widthIn(0.dp, 240.dp)
                .wrapContentHeight()
                .background(
                    SimpleWhite,
                    RoundedCornerShape(
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = message,
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 22.sp
        )
    }
}

@Composable
@Preview
fun ItemFromMessagePreview() {
    ItemFromMessage("hahaha obviously")
}