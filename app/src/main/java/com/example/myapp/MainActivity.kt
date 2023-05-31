package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapp.game_view.View
import com.example.myapp.ui.theme.MyappTheme

class MainActivity : ComponentActivity() {

    private var currentNumber: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyappTheme {
                currentNumber = prefs.number
                View()
            }
        }
    }
}
