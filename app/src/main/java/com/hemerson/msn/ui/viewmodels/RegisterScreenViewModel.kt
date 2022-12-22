package com.hemerson.msn.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hemerson.msn.ui.state.RegisterScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterScreenViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<RegisterScreenUiState> =
        MutableStateFlow(RegisterScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onImageUriChange = {
                    _uiState.value = _uiState.value.copy(
                        imageUri = it
                    )
                },

                onBitmapChange = {
                    _uiState.value = _uiState.value.copy(
                        bitmap = it
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onEmailChange = {
                    _uiState.value = _uiState.value.copy(
                        email = it
                    )
                },
                onPasswordChange = {
                    _uiState.value = _uiState.value.copy(
                        password = it
                    )
                },
                onRepeatPasswordChange = {
                    _uiState.value = _uiState.value.copy(
                        repeatPassword = it
                    )
                }
            )
        }
    }
}