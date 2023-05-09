package com.example.greatgamelibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class GameActivity : AppCompatActivity() {
    var position = 0
    lateinit var mainMenuButton: Button
    lateinit var rateButton: Button
    lateinit var firebaseDB: FirebaseDB
    lateinit var image: ImageView
    lateinit var recyclerView: RecyclerView
    var gameInfo: ArrayList<GameInfo> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        firebaseDB = FirebaseDB()
        mainMenuButton = findViewById(R.id.MainMenuButton)
        rateButton = findViewById(R.id.RateGameButton)
        image = findViewById(R.id.GameImage)
        recyclerView = findViewById(R.id.recyclerView)
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
        var gameImage = firebaseDB.gameImage
        var list = arrayListOf<GameInfoData>()
        if(!gameInfo.isEmpty()){
            list.add(GameInfoData(gameInfo[position].title))
            list.add(GameInfoData(gameInfo[position].data))
            list.add(GameInfoData(gameInfo[position].rating))
            list.add(GameInfoData(gameInfo[position].userRating))
            var adapter = GameInfoAdapter(list)
            image.setImageBitmap(gameImage[position].image)
            recyclerView.adapter =adapter
        }
        //Toast.makeText(this@GameActivity,gameInfo.toString(),Toast.LENGTH_SHORT).show()

    }
}