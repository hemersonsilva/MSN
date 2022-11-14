package com.hemerson.msn.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.R
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.theme.SimpleWhite

@Composable
fun MsnButtonRound(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    showLoading: Boolean = false,
    verticalPaddingValues: Dp = 0.dp,
    action: () -> Unit
) {
    Button(
        onClick = { action.invoke() },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkBlue,
            contentColor = SimpleWhite
        ),
        modifier = modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        contentPadding = PaddingValues(verticalPaddingValues)
    ) {
        if (showLoading) CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            color = SimpleWhite,
            strokeWidth = (1.5).dp
        )
        else Text(
            text = stringResource(id = text),
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp
        )
    }
}

@Composable
@Preview
fun SkButtonRoundPreview() {
    MsnButtonRound(
        modifier = Modifier.padding(top = 32.dp),
        text = R.string.app_name,
        action = { },
    )
}
