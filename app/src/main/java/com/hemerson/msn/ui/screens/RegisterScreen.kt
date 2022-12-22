package com.hemerson.msn.ui.screens

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hemerson.msn.R
import com.hemerson.msn.ui.components.MsnButtonRound
import com.hemerson.msn.ui.components.MsnTextField
import com.hemerson.msn.ui.state.RegisterScreenUiState
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.SimpleWhite
import com.hemerson.msn.ui.viewmodels.RegisterScreenViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterScreenViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    RegisterScreen(
        state = state,
        context = context,
        onRegisterClick = {
            Toast.makeText(context, "Em desenvolvimento...", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterScreenUiState = RegisterScreenUiState(),
    context: Context,
    onRegisterClick: () -> Unit = {}
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(SimpleWhite)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            state.onImageUriChange(uri)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UserImage(state = state, context = context, launcher = launcher)
        UserImagePlaceholder(state = state, launcher = launcher)

        val name = state.name
        val email = state.email
        val password = state.password
        val repeatPassword = state.repeatPassword

        Column {
            Text(
                text = "Name",
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
                lineHeight = 24.sp
            )

            MsnTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = name,
                updatedValue = state.onNameChange,
                placeholderText = "Type your name",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                isEmailOrPassword = true
            )

            Text(
                text = "E-mail",
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
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
                modifier = Modifier.padding(top = 16.dp),
                lineHeight = 24.sp
            )

            MsnTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = password,
                updatedValue = state.onPasswordChange,
                placeholderText = "Type your password",
                keyboardType = KeyboardType.Password,
                showPassIcon = true,
                imeAction = ImeAction.Next,
                isEmailOrPassword = true
            )

            Text(
                text = "Repeat password",
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp),
                lineHeight = 24.sp
            )

            MsnTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = repeatPassword,
                updatedValue = state.onRepeatPasswordChange,
                placeholderText = "Repeat password",
                keyboardType = KeyboardType.Password,
                showPassIcon = true,
                imeAction = ImeAction.Done,
                isEmailOrPassword = true
            )

            MsnButtonRound(
                modifier = Modifier.padding(top = 24.dp),
                text = R.string.register,
                verticalPaddingValues = 16.dp,
                action = onRegisterClick
            )
        }
    }
}

@Composable
private fun UserImagePlaceholder(
    state: RegisterScreenUiState,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    if (state.bitmap == null) {
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .clickable {
                    launcher.launch("image/*")
                }
        )
    }
}

@Composable
private fun UserImage(
    state: RegisterScreenUiState,
    context: Context,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {

    val imageUri = state.imageUri
    val bitmap = state.bitmap

    imageUri.let { uri ->
        if (Build.VERSION.SDK_INT < 28) {

            state.onBitmapChange(
                MediaStore
                    .Images
                    .Media
                    .getBitmap(context.contentResolver, uri)
            )
        } else {
            val source = uri?.let {
                ImageDecoder.createSource(context.contentResolver, it)
            }
            state.onBitmapChange(source?.let { ImageDecoder.decodeBitmap(it) })
        }
    }

        bitmap?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    }
            )
        }
}

@Preview(showBackground = true)
@Composable
private fun RegisterViewPreview() {
    RegisterScreen(RegisterScreenViewModel())
}