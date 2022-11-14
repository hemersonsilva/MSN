package com.hemerson.msn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.R
import com.hemerson.msn.ui.theme.Attention
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.theme.LightBlue

@Composable
fun MsnTextField(
    modifier: Modifier = Modifier,
    value: String,
    updatedValue: (String) -> Unit,
    placeholderText: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions = KeyboardActions(),
    errorMessage: Int? = null,
    showPassIcon: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    isEmailOrPassword: Boolean = false
) {
    var inputValue by remember { mutableStateOf(value) }
    var visiblePass by remember { mutableStateOf(!showPassIcon) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LightBlue, RoundedCornerShape(100))
    ) {
        TextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                updatedValue.invoke(it)
            },
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            isError = errorMessage != null,
            readOnly = readOnly,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            singleLine = true,
            visualTransformation = if (visiblePass) VisualTransformation.None else PasswordVisualTransformation(
                mask = '*'
            ),
            placeholder = {
                Text(
                    text = placeholderText,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },

            trailingIcon = {
                if (showPassIcon)
                    Icon(
                        painter = painterResource(id = if(visiblePass) R.drawable.ic_outline_visibility_24 else R.drawable.ic_baseline_visibility_off_24),
                        contentDescription = null,
                        tint = DarkBlue,
                        modifier = Modifier.clickable {
                            visiblePass = !visiblePass
                        }
                    )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                capitalization = if (isEmailOrPassword) KeyboardCapitalization.None else KeyboardCapitalization.Sentences
            ),
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Attention,
                focusedLabelColor = DarkBlue,
                unfocusedLabelColor = DarkBlue,
                cursorColor = DarkBlue,
            ),
            textStyle = TextStyle(
                color = Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 23.sp
            )
        )
        errorMessage?.let {
            Text(
                text = stringResource(id = it),
                color = Attention,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
    }
}

@Preview
@Composable
fun MsnTextFieldPreview() {
    MsnTextField(
        value = "",
        updatedValue = {},
        placeholderText = "Type your e-mail",
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Go,
        showPassIcon = true
    )
}