package com.hemerson.msn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemerson.msn.R
import com.hemerson.msn.ui.components.MsnButtonRound
import com.hemerson.msn.ui.components.MsnTextField
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.DarkBlue

@Composable
fun LoginView() {

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_waves),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.msn_logo),
                contentDescription = null,
                modifier = Modifier.size(116.dp)
            )
        }

        var inputEmail by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(vertical = 36.dp, horizontal = 24.dp)) {
            Text(
                text = "Welcome back!",
                color = Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )
            Text(
                text = "E-mail",
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp),
                lineHeight = 24.sp
            )

            MsnTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = inputEmail,
                updatedValue = {
                    inputEmail = it
                },
                placeholderText = "Type your email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isEmailOrPassword = true
            )

            Text(
                text = "Password",
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 32.dp),
                lineHeight = 24.sp
            )

            MsnTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = inputEmail,
                updatedValue = {
                    inputEmail = it
                },
                placeholderText = "Type your password",
                keyboardType = KeyboardType.Password,
                showPassIcon = true,
                imeAction = ImeAction.Go,
                isEmailOrPassword = true
            )

            Text(
                text = "Forgot Password?",
                color = DarkBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(End),
                lineHeight = 24.sp
            )

            MsnButtonRound(
                modifier = Modifier.padding(top = 32.dp),
                text = R.string.login,
                verticalPaddingValues = 16.dp,
            ) {
                Toast.makeText(context, "Em desenvolvimento...", Toast.LENGTH_SHORT).show()
            }

            Text(
                modifier = Modifier.padding(top = 24.dp).align(CenterHorizontally),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    ) {
                        append("Don’t have an account? ")
                    }
                    append("Sign up")
                },
                color = DarkBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    LoginView()
}

