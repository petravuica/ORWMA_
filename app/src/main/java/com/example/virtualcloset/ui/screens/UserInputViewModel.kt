package com.example.virtualcloset.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.virtualcloset.ui.data.UserDataUiEvent
import com.example.virtualcloset.ui.data.UserInputScreenState

class UserInputViewModel : ViewModel() {
    var uiState = mutableStateOf(UserInputScreenState())

    fun onEvent(event: UserDataUiEvent){
        when(event){
            is UserDataUiEvent.UserNameEntered -> {
                uiState.value = uiState.value.copy(
                    nameEntered = event.name
                )
            }
        }
    }
}



