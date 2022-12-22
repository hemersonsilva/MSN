package com.hemerson.msn.ui.state

import android.graphics.Bitmap
import android.net.Uri

data class RegisterScreenUiState(
    val imageUri: Uri? = null,
    val bitmap: Bitmap? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val onImageUriChange: (Uri?) -> Unit = {},
    val onBitmapChange: (Bitmap?) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onRepeatPasswordChange: (String) -> Unit = {}
)