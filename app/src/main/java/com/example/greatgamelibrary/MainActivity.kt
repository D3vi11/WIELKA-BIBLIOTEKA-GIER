package com.example.greatgamelibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlin.coroutines.coroutineContext

class
MainActivity : AppCompatActivity() {
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
        //getUserData()
        searchButton.setOnClickListener {
            getUserData()
        }
    }

    fun getUserData() {
        var gameInfo = firebaseDB.getDataFromDB()
        var gameItems = arrayListOf<GameItem>()
        var gameImage = firebaseDB.gameImage
        for (i in gameInfo.indices) {
            gameItems.add(GameItem(gameImage[i].image, gameInfo[i].title))
        }
        var adapter = GameItemAdapter(gameItems)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : GameItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity,GameActivity::class.java)
                intent.putExtra("position",position)
                startActivity(intent)
            }

        })

        //Toast.makeText(this@MainActivity,gameInfo[0].toString(),Toast.LENGTH_SHORT).show()
    }

    fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

}