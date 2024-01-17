package com.example.virtualcloset.ui.data

sealed class UserDataUiEvent{
    data class UserNameEntered(val name:String) : UserDataUiEvent()

}