package com.code.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.code.demo.ui.screens.fruit.FruitScreen
import com.code.demo.ui.screens.FruitViewModel
import com.code.demo.ui.theme.FruitAppTheme


class MainActivity : ComponentActivity() {

    private val fruitViewModel by viewModels<FruitViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruitAppTheme {
               FruitScreen(fruitViewModel)
            }
        }
        enableEdgeToEdge()
    }
}

