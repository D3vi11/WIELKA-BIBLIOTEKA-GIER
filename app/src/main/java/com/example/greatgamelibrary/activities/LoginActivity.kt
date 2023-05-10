package com.example.greatgamelibrary.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.greatgamelibrary.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var loginText: TextView
    lateinit var passwordText: TextView
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var mainMenuButton: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        loginText = findViewById(R.id.Login)
        passwordText = findViewById(R.id.Password)
        loginButton = findViewById(R.id.LoginButton)
        registerButton = findViewById(R.id.RegisterButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        loginButton.setOnClickListener {
            login(loginText.text.toString(),passwordText.text.toString())
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

    fun login(email:String,password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this@LoginActivity, GameActivity::class.java)
                    intent.putExtra("isLoggedIn",true)
                    startActivity(intent)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
                }
            }
    }
}