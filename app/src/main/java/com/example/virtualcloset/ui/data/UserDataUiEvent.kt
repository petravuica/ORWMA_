package com.example.virtualcloset.ui.data

import java.net.URL

sealed class UserDataUiEvents{

    data class imageSelected(val imageURL: String) : UserDataUiEvents()
    data class typeSelected(val type: String) : UserDataUiEvents()
    data class notesEntered(val notes : String) : UserDataUiEvents()

}