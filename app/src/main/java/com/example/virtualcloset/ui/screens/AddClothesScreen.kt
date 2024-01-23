package com.example.virtualcloset.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.virtualcloset.R
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.virtualcloset.ui.data.DropDownItem
import com.example.virtualcloset.ui.data.UserDataUiEvents
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun AddClothesScreen(userInputViewModel: UserInputViewModel) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
            userInputViewModel.onEvent(UserDataUiEvents.imageSelected(it.toString()))

        })

    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.purple)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Title("Virtual Closet")
            Spacer(modifier = Modifier.size(10.dp))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color.White),
                model = selectedImageUri,
                contentDescription = null)
            Spacer(modifier = Modifier.size(20.dp))

            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
               
                LazyColumn(
                    modifier = Modifier
                        .width(140.dp)
                        .height(56.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        listOf(
                            "Choose type"
                        )
                    ) { name ->
                        var selectedType by remember { mutableStateOf(name) }

                        ChooseType(
                            textScreen = selectedType,
                            dropDownItems = listOf(
                                DropDownItem("T-shirt"),
                                DropDownItem("Jeans"),
                                DropDownItem("Shoes"),
                            ),
                            onItemClick = {
                                Toast.makeText(
                                    context,
                                    it.text,
                                    Toast.LENGTH_LONG
                                ).show()
                                selectedType = it.text
                                userInputViewModel.onEvent(UserDataUiEvents.typeSelected(it.text))
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.size(45.dp))
                Button(
                    modifier = Modifier
                        .height(56.dp)
                        .width(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        if (selectedImageUri != null) {
                            userInputViewModel.onEvent(UserDataUiEvents.imageSelected(selectedImageUri.toString()))
                        }
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_galley),
                        contentDescription = "Add image",
                        modifier = Modifier
                            .size(128.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .height(56.dp)
                        .width(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Take a picture",
                        modifier = Modifier.size(32.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.size(5.dp))
            TextFieldComponent(
                onTextChanged = {
                userInputViewModel.onEvent(
                    UserDataUiEvents.notesEntered(it)
                )
            })
            Spacer(modifier = Modifier.size(50.dp))
             val db = Firebase.firestore
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Button(
                    onClick = {
                        val imageUrl = selectedImageUri.toString()
                        val type =
                            userInputViewModel.uiState.value.type // Ovo bi trebalo biti ažurirano od strane ViewModel-a
                        val notes =
                            userInputViewModel.uiState.value.notes // Ovo bi trebalo biti ažurirano od strane ViewModel-a

                        val clothingItem = ClothingItem(
                            imageUrl = imageUrl,
                            type = type,
                            notes = notes
                        )
                        db.collection("clothes")
                            .add(clothingItem)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Saved!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error saving! ",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }


                    }) {
                    Text(text = "ADD")

                }
            }
            Spacer(modifier = Modifier.size(5.dp))

            }


        }
    }
@Preview
@Composable
fun AddClothesScreenPreview() {
    AddClothesScreen(UserInputViewModel())
}
