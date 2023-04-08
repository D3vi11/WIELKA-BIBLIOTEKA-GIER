package com.example.greatgamelibrary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var editText: EditText = findViewById(R.id.editText)
        var searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            Toast.makeText(this,editText.text,Toast.LENGTH_SHORT).show()
        }
    }
}