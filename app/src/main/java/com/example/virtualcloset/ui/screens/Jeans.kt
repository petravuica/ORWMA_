package com.example.virtualcloset.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.virtualcloset.R

@Composable
fun Jeans(
    navController: NavController,
    clothingViewModel: ClothingViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.purple)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Title(textValue = "Jeans")
            Spacer(modifier = Modifier.size(10.dp))
            TopBar(navController)
            DisposableEffect(Unit) {
                clothingViewModel.getClothesFromFirestore(type = "Jeans")
                onDispose { }
            }

            // Prikaz liste odjeće
            ClothingListScreen(clothingViewModel)
        }
    }

}

@Preview
@Composable
fun JeansPreview() {

}