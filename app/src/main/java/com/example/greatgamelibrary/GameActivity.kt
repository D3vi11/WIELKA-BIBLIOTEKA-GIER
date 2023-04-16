package com.example.greatgamelibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    var position = 0
    lateinit var mainMenuButton: Button
    lateinit var rateButton: Button
    lateinit var firebaseDB: FirebaseDB
    lateinit var image: ImageView
    var gameInfo: ArrayList<GameInfo> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        firebaseDB = FirebaseDB()
        mainMenuButton = findViewById(R.id.MainMenuButton)
        rateButton = findViewById(R.id.RateGameButton)
        image = findViewById(R.id.GameImage)
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
        gameInfo = firebaseDB.getDataFromDB()
        if(!gameInfo.isEmpty()){
            GameInfoAdapter(gameInfo[position])
            image.setImageBitmap(gameInfo[position].image)
        }
        //Toast.makeText(this@GameActivity,gameInfo.toString(),Toast.LENGTH_SHORT).show()

    }
}