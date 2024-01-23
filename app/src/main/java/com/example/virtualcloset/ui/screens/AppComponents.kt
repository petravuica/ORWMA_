package com.example.virtualcloset.ui.screens

import androidx.compose.runtime.*
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.virtualcloset.R
import java.util.Objects
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.virtualcloset.ui.data.DropDownItem

import org.w3c.dom.Text
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun Title(
    textValue:String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(50.dp),
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        text = textValue
    )
}
@Preview
@Composable
fun TitlePreview() {
    Title("Ormar")
}

@Composable
fun TextComponent(textValue:String, textSize:TextUnit, colorValue : Color = Color.Black) {
    Text(
        text = textValue,
        fontSize = textSize,
        color = colorValue,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun TopBar(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.purple))
            .horizontalScroll(scrollState)
        
    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Red),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = { navController.navigate(Routes.ADD_CLOTHES_SCREEN) },

        ) {
            TextComponent(textValue = "ADD", textSize = 18.sp)
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Red),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = {
                navController.navigate(Routes.T_SHIRT)
            }) {
            TextComponent(textValue = "T-SHITS", textSize = 18.sp)
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Red),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = { navController.navigate(Routes.JEANS) }) {
            TextComponent(textValue = "JEANS", textSize = 18.sp)
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Red),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = { navController.navigate(Routes.SHOES) }) {
            TextComponent(textValue = "SHOES", textSize = 18.sp)
        }

    }
}


@Composable
fun TextFieldComponent(
    onTextChanged : (name:String) -> Unit
) {
    var currentValue by remember{
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = currentValue,
        onValueChange = {
            currentValue = it
            onTextChanged(it)
        },
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Notes", fontSize = 18.sp, textAlign = TextAlign.Center
            )
        },
        textStyle = TextStyle.Default.copy(fontSize = 24.sp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        }
        )
}

@Preview
@Composable
fun TextFieldComponentPreview() {
    TextFieldComponent(onTextChanged = {})
}

@Composable
fun ChooseType(
    textScreen : String,
    dropDownItems : List<DropDownItem>,
    onItemClick: (DropDownItem) -> Unit
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .onSizeChanged {
            itemHeight = with(density){ it.height.toDp() }
        }

    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        }
                    )
                }
                .padding(16.dp)
        ){
            Text(text = textScreen)
        }

        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false })
        {
            dropDownItems.forEach{ item ->
                DropdownMenuItem(
                   text = { Text(text = item.text) },
                   onClick = {
                       onItemClick(item)
                       isContextMenuVisible = false
                   })
            }
        }
    }
}


@Composable
fun ClothesItem(clothesItem : ClothingItem) {
    Card(
        modifier = Modifier.padding(end = 8.dp),
        shape = RoundedCornerShape(4.dp),

    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .height(100.dp)
                .background(color = Color.LightGray)
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(70.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color.White),
                model = clothesItem.imageUrl,
                contentDescription = null)
            Text(text = clothesItem.notes, textAlign = TextAlign.Center)
        }
    }
}
@Preview
@Composable
fun ClothesItemPreview() {

}
@Composable
fun ClothingListScreen(clothingViewModel: ClothingViewModel) {
    val clothes = clothingViewModel.clothes

    LazyColumn {
        items(clothes) { clothingItem ->
            // Prikaz pojedinačnog odjevnog predmeta
            ClothingItemComponent(clothingItem)
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}


@Composable
fun ClothingItemComponent(clothingItem: ClothingItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            // Prikaz slike
            if (clothingItem.imageUrl.isNotEmpty()) {
                val uri = Uri.parse(clothingItem.imageUrl)
                AsyncImage(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.White),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uri)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            } else {
                // Prikaz placeholdera ili drugog prikaza ako nema slike
                Text(
                    text = "No Image",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .height(200.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.Gray)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            // Prikaz bilješki
            Text(
                text = "Notes: ${clothingItem.notes}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp).fillMaxWidth()
            )
        }
    }
}
