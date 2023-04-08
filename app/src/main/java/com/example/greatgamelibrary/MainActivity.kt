package com.example.greatgamelibrary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var searchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var gameItems: ArrayList<GameItem>
    lateinit var gameImage: ArrayList<Int>
    lateinit var gameTitle: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        gameImage = arrayListOf(R.drawable.a,R.drawable.b,R.drawable.a,R.drawable.b,R.drawable.a)
        gameTitle = arrayListOf("Wiedźmin","Gothic","Mass Effect", "Red Dead Redemption", "League of Legends")
        gameItems = arrayListOf<GameItem>()
        searchButton.setOnClickListener {
            Toast.makeText(this,editText.text,Toast.LENGTH_SHORT).show()
            getUserData()
        }
    }
    fun getUserData(){
        for(i in gameImage.indices){
            gameItems.add(GameItem(gameImage[i],gameTitle[i]))
        }
        recyclerView.adapter = GameItemAdapter(gameItems)
    }
}