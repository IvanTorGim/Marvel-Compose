package com.example.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.marvelcompose.ui.screens.caracters.CharacterScreen
import com.example.marvelcompose.ui.theme.MarvelComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelApp { paddingValues ->
                CharacterScreen(
                    paddingValues = paddingValues,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun MarvelApp(content: @Composable (PaddingValues) -> Unit) {
    MarvelComposeTheme {
        Scaffold { paddingValues ->
            content(paddingValues)
        }
    }
}