package com.example.greatgamelibrary

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
        //getUserData()
        searchButton.setOnClickListener {
            getUserData()
        }
    }

    fun getUserData() {
        var gameTitle = arrayListOf<String>()
        var gameImage = arrayListOf<Bitmap>()
        val gameItems = arrayListOf<GameItem>()
        CoroutineScope(IO).launch {
                //delay(1000)
                gameTitle = firebaseDB.getTitleDataFromDB()
                gameImage = firebaseDB.getImageDataFromDB()
        }
        //while(gameImage.isEmpty())
        Thread.sleep(500)
        if (gameTitle.size == gameImage.size) {
            for (i in gameImage.indices) {
                gameItems.add(GameItem(gameImage[i], gameTitle[i]))
            }
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
    }

    fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}