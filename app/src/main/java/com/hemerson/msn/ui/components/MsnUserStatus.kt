package com.hemerson.msn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun MsnUserStatus(userStatusColor: Color, isToolbar: Boolean = false) {

    val size = if (isToolbar) 20.dp else 18.dp
    val boxSize = with(LocalDensity.current) { size.toPx() }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .offset(x = 36.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(size = size)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(userStatusColor, Color.Transparent),
                        center = Offset(x = boxSize / 2, y = boxSize / 2) // center
                    )
                )
        )
        Box(
            modifier = Modifier
                .size(size = size - 8.dp)
                .clip(CircleShape)
                .background(userStatusColor)
        )
    }
}