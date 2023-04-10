package com.example.greatgamelibrary

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    var position = 0
    lateinit var mainMenuButton: Button
    lateinit var rateButton: Button
    lateinit var title: TextView
    lateinit var date: TextView
    lateinit var rating: TextView
    lateinit var userRating: TextView
    lateinit var gameImage: ImageView
    lateinit var firebaseDB: FirebaseDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        firebaseDB = FirebaseDB()
        mainMenuButton = findViewById(R.id.MainMenuButton)
        rateButton = findViewById(R.id.RateGameButton)
        title = findViewById(R.id.GameTitleText)
        date = findViewById(R.id.DateText)
        rating = findViewById(R.id.RatingText)
        userRating = findViewById(R.id.UserRatingText)
        gameImage = findViewById(R.id.GameImage)
        position = intent.getIntExtra("position",0)
        //setAllData()
        mainMenuButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        rateButton.setOnClickListener {
            setAllData()
        }
    }
    fun setAllData(){
        var title = arrayListOf<String>()
        var image = arrayListOf<Bitmap>()
        var date = arrayListOf<String>()
        var rating = arrayListOf<String>()
        var userRating = arrayListOf<String>()
        var thread = Thread(){
            Runnable {
                //title = firebaseDB.getTitleDataFromDB()
               // image = firebaseDB.getImageDataFromDB()
                date = firebaseDB.getTextDataFromDB("date")
                rating = firebaseDB.getTextDataFromDB("rating")
                userRating = firebaseDB.getTextDataFromDB("userRating")
            }
        }
        thread.start()
        if(!title.isEmpty()){
            this.title.text = title.get(position)
        }else{
            Toast.makeText(this@GameActivity,"ERROR",Toast.LENGTH_SHORT).show()
        }
        if(!image.isEmpty()){
            gameImage.setImageBitmap(image.get(position))
        }else{
            Toast.makeText(this@GameActivity,"ERROR",Toast.LENGTH_SHORT).show()
        }
        if(!date.isEmpty()){
            this.date.text = date.get(position)
        }else{
            Toast.makeText(this@GameActivity,"ERROR",Toast.LENGTH_SHORT).show()
        }
        if(!rating.isEmpty()){
            this.rating.text = rating.get(position)
        }else{
            Toast.makeText(this@GameActivity,"ERROR",Toast.LENGTH_SHORT).show()
        }
        if(!userRating.isEmpty()){
            this.userRating.text = userRating.get(position)
        }else{
            Toast.makeText(this@GameActivity,"ERROR",Toast.LENGTH_SHORT).show()
        }
    }
}