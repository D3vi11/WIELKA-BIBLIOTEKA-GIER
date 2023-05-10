package com.example.greatgamelibrary.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.greatgamelibrary.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginText: EditText
    lateinit var passwordText: EditText
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var mainMenuButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginText = findViewById(R.id.Login)
        passwordText = findViewById(R.id.Password)
        loginButton = findViewById(R.id.LoginButton)
        registerButton = findViewById(R.id.RegisterButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        loginButton.setOnClickListener {
            login()
        }
        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.putExtra("isLoggedIn",false)
            startActivity(intent)
        }
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",false)
            startActivity(intent)
        }
    }

    fun login(){

    }
}