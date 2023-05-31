package com.example.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

@Suppress("DEPRECATION")
class SecondActivity : ComponentActivity() {

    private var currentNumber = 0
    private val startSecond: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_main)

        currentNumber = prefs.number
        findViewById<TextView>(R.id.highScoreText).text = "$currentNumber"

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            startActivityForResult(Intent(this, MainActivity::class.java), startSecond)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == startSecond) && (resultCode == Activity.RESULT_OK)) {
            currentNumber = prefs.number
            findViewById<TextView>(R.id.highScoreText).text = "$currentNumber"
        }
    }
}