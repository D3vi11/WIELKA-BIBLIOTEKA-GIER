package com.example.greatgamelibrary.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.greatgamelibrary.R

class RegisterActivity : AppCompatActivity() {
    lateinit var login: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var passwordRepeat: EditText
    lateinit var registerButton: Button
    lateinit var mainMenuButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        login = findViewById(R.id.Login)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.Password)
        passwordRepeat = findViewById(R.id.PasswordRepeat)
        registerButton = findViewById(R.id.RegisterButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        registerButton.setOnClickListener {
            register()
        }
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",false)
            startActivity(intent)
        }
    }
    fun register(){

    }
}