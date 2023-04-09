package com.example.greatgamelibrary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var searchButton: Button
    lateinit var advancedSearchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var firebaseDB: FirebaseDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseDB = FirebaseDB()
        editText = findViewById(R.id.editText)
        searchButton = findViewById(R.id.searchButton)
        advancedSearchButton = findViewById(R.id.advancedSearchButton)
        setRecyclerView()
        //TODO("dlaczego getUserData nie działa na starcie")
        getUserData()
        searchButton.setOnClickListener {
            getUserData()
        }
    }

    fun getUserData() {
        val gameTitle = firebaseDB.getTitleDataFromDB()
        val gameImage = firebaseDB.getImageDataFromDB()
        val gameItems = arrayListOf<GameItem>()
        if (gameTitle.size == gameImage.size) {
            for (i in gameImage.indices) {
                gameItems.add(GameItem(gameImage[i], gameTitle[i]))
            }
        }
        recyclerView.adapter = GameItemAdapter(gameItems)
    }

    fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}