package com.hemerson.msn.register

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var imageUri: MutableState<Uri?> = mutableStateOf<Uri?>(null)
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)
    var name: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val confirmPassword: MutableState<String> = mutableStateOf("")
}