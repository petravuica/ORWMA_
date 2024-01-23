package com.example.virtualcloset.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.virtualcloset.R
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add  // ok
import androidx.compose.material3.ElevatedButton



@Composable
fun UserInputScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.purple)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()

        ){
            Title("Virtual Closet")
            Spacer(modifier = Modifier.height(5.dp))
            TopBar(navController)
            Spacer(modifier = Modifier.height(260.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                text = "Add your clothes here"
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                ElevatedButton(onClick = {
                    navController.navigate(Routes.ADD_CLOTHES_SCREEN)
                },
                    modifier= Modifier
                        .size(50.dp)
                        .fillMaxWidth(), //avoid the oval shape

                    elevation = ButtonDefaults.buttonElevation(

                    ),
                    shape = CircleShape,
                    border= BorderStroke(1.dp, colorResource(id = R.color.purple_500)),
                    contentPadding = PaddingValues(0.dp),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor =  colorResource(id = R.color.purple_500))
                ) {
                    Icon(Icons.Default.Add, contentDescription = "add clothes")
                }
            }

        }

    }
}

