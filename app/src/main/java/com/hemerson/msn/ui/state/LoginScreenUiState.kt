package com.hemerson.msn.ui.state

data class LoginScreenUiState(
    val email: String = "",
    val password: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {}
)