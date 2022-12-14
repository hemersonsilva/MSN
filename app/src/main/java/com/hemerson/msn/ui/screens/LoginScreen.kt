package com.hemerson.msn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
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
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hemerson.msn.R
import com.hemerson.msn.routes.Routes
import com.hemerson.msn.ui.components.MsnButtonRound
import com.hemerson.msn.ui.components.MsnTextField
import com.hemerson.msn.ui.state.LoginScreenUiState
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.Blue
import com.hemerson.msn.ui.theme.DarkBlue
import com.hemerson.msn.ui.viewmodels.LoginScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    navController: NavHostController
){
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    LoginScreen(
        state = state,
        onLoginClick = {
            Toast.makeText(context, "Em desenvolvimento...", Toast.LENGTH_SHORT).show()
        },
        onSignUpClick = {
            navController.navigate(Routes.Register.routeName)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    state: LoginScreenUiState = LoginScreenUiState(),
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
) {

    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Blue)

    val email = state.email
    val password = state.password

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
                value = email,
                updatedValue = state.onEmailChange,
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
                modifier = Modifier
                    .padding(top = 12.dp)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = password,
                updatedValue = state.onPasswordChange,
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
                modifier = Modifier
                    .padding(top = 32.dp)
                    .bringIntoViewRequester(bringIntoViewRequester),
                text = R.string.login,
                verticalPaddingValues = 16.dp,
                action = onLoginClick
            )

            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(CenterHorizontally)
                    .clickable { onSignUpClick() },
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    ) {
                        append("Don???t have an account? ")
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
    LoginScreen()
}

