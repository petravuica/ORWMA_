package com.example.virtualcloset.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
class ClothingViewModel : ViewModel() {
    private val _clothes = mutableStateListOf<ClothingItem>()
    val clothes: List<ClothingItem> get() = _clothes

    fun getClothesFromFirestore(type: String) {
        val db = Firebase.firestore
        db.collection("clothes")
            .whereEqualTo("type", type)
            .get()
            .addOnSuccessListener { result ->
                _clothes.clear()
                for (document in result) {
                    val imageUrl = document.getString("imageUrl") ?: ""
                    val notes = document.getString("notes") ?: ""
                    _clothes.add(ClothingItem(imageUrl = imageUrl, type = type, notes = notes))
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors
                Log.e("ClothingViewModel", "Error getting clothes", exception)
            }
    }
}

