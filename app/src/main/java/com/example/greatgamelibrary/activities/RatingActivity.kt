package com.example.greatgamelibrary.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.greatgamelibrary.R
import com.example.greatgamelibrary.database.FirebaseDB
import com.example.greatgamelibrary.interfaces.ActivityInterface

class RatingActivity : AppCompatActivity(),ActivityInterface {
    lateinit var image: ImageView
    lateinit var title: TextView
    lateinit var rateButton: Button
    lateinit var mainMenuButton: Button
    lateinit var firebaseDB: FirebaseDB
    var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        image = findViewById(R.id.GameImage)
        title = findViewById(R.id.GameTitle)
        rateButton = findViewById(R.id.RateButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        firebaseDB = FirebaseDB(this@RatingActivity)
        firebaseDB.getDataFromDB()
        position = intent.getIntExtra("position",0)
        rateButton.setOnClickListener {
            rate()
        }
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@RatingActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",true)
            startActivity(intent)
        }
    }

    override fun onUpdate() {
        image.setImageBitmap(firebaseDB.gameImage[position].image)
        title.text = firebaseDB.gameDataList[position].title
    }
    fun rate(){

    }
}