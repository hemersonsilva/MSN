package com.hemerson.msn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hemerson.msn.R

@Composable
fun LoginView(){

    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.bg_waves),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }



}

@Preview
@Composable
fun LoginViewPreview(){
    LoginView()
}

