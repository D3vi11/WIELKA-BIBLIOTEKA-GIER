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
        val  title = firebaseDB.getTitleDataFromDB()
        val  image = firebaseDB.getImageDataFromDB()
        val  date = firebaseDB.getTextDataFromDB("date")
        val  rating = firebaseDB.getTextDataFromDB("rating")
        val  userRating = firebaseDB.getTextDataFromDB("userRating")

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
            //Toast.makeText(this@GameActivity,title.toString(),Toast.LENGTH_SHORT).show()
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