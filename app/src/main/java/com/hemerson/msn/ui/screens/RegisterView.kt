package com.hemerson.msn.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
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
import com.hemerson.msn.ui.theme.Black
import com.hemerson.msn.ui.theme.SimpleWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterView() {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(SimpleWhite)

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserImage(imageUri, bitmap, context, launcher)
        UserImagePlaceholder(bitmap, launcher)

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
                value = "",
                updatedValue = { },
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
                value = "",
                updatedValue = {},
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
                modifier = Modifier
                    .padding(top = 12.dp)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = "",
                updatedValue = { },
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
                modifier = Modifier
                    .padding(top = 12.dp)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = "",
                updatedValue = { },
                placeholderText = "Repeat password",
                keyboardType = KeyboardType.Password,
                showPassIcon = true,
                imeAction = ImeAction.Done,
                isEmailOrPassword = true
            )

            MsnButtonRound(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .bringIntoViewRequester(bringIntoViewRequester),
                text = R.string.register,
                verticalPaddingValues = 16.dp,
            ) {
                Toast.makeText(context, "Em desenvolvimento...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun UserImagePlaceholder(
    bitmap: MutableState<Bitmap?>,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    if (bitmap.value == null) {
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
    imageUri: Uri?,
    bitmap: MutableState<Bitmap?>,
    context: Context,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore
                .Images
                .Media
                .getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let { btm ->
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
}


@Preview(showBackground = true)
@Composable
private fun RegisterViewPreview() {
    RegisterView()
}