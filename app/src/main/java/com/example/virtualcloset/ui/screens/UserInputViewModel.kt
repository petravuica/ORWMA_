package com.example.virtualcloset.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.virtualcloset.ui.data.UserDataUiEvents

class UserInputViewModel : ViewModel() {

    companion object{
        const val TAG = "UserInputViewModel"
    }
    var uiState = mutableStateOf(ClothingItem())

    //ova funkcija se dogadja kada se bilo kakva promjena dogodi na screenu
    fun onEvent(event: UserDataUiEvents){
        when(event){
            is UserDataUiEvents.imageSelected -> {
                uiState.value = uiState.value.copy(
                    imageUrl = event.imageURL
                )
                Log.d(TAG, "onEvent: imageSelected")
                Log.d(TAG, "${uiState.value}")
            }
            is UserDataUiEvents.typeSelected -> {
                uiState.value = uiState.value.copy(
                    type = event.type
                )
                Log.d(TAG, "onEvent: typeSelected")
                Log.d(TAG, "${uiState.value}")
            }
            is UserDataUiEvents.notesEntered -> {
                uiState.value = uiState.value.copy(
                    notes = event.notes
                )
                Log.d(TAG, "onEvent: notesEntered")
                Log.d(TAG, "${uiState.value}")
            }
        }
    }
}





