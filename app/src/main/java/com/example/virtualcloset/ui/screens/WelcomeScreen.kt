package com.example.virtualcloset.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize().clickable {
            navController.navigate(Routes.USER_INPUT_SCREEN)
        },
        color = colorResource(id = R.color.purple)
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth().height(500.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            text = "Welcome to \n \n Virtual Closet"
        )
    }
}
@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())

}