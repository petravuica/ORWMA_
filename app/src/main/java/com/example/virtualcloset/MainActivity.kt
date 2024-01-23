package com.example.virtualcloset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.virtualcloset.ui.screens.Routes
import com.example.virtualcloset.ui.screens.UserInputScreen
import com.example.virtualcloset.ui.screens.VirtualClosetNavigationGraph
import com.example.virtualcloset.ui.screens.WelcomeScreen
import com.example.virtualcloset.ui.theme.VirtualClosetTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VirtualClosetTheme {
                VirtualClosetApp()
            }
        }
    }

    @Composable
    fun VirtualClosetApp() {
        VirtualClosetNavigationGraph()
    }
}

