package com.example.greatgamelibrary.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.greatgamelibrary.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var login: TextView
    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var passwordRepeat: TextView
    lateinit var registerButton: Button
    lateinit var mainMenuButton: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        login = findViewById(R.id.Login)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.Password)
        passwordRepeat = findViewById(R.id.PasswordRepeat)
        registerButton = findViewById(R.id.RegisterButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        registerButton.setOnClickListener {
            println(password.text)
            println(passwordRepeat.text)
            if(password.text.toString().length>=6){
                if(password.text.toString().equals(passwordRepeat.text.toString())){
                    register(email.text.toString(),password.text.toString())
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.putExtra("isLoggedIn",false)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@RegisterActivity,"Passwords are not equal",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@RegisterActivity,"Password should be at least 6 characters",Toast.LENGTH_SHORT).show()
            }
        }
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",false)
            startActivity(intent)
        }
    }
    fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}