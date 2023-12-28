package com.example.atmosphere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiCall().fetchData(this) { userData ->
            displayResponse(userData.data.first_name)
        }
    }

    fun displayResponse(response : String) {
        val text = findViewById<TextView>(R.id.textView)
        text.text = response
    }
}