package com.example.greatgamelibrary

import android.content.Intent
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
    lateinit var gameInfo: ArrayList<GameInfo>

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
            //Toast.makeText(this,"abc",Toast.LENGTH_SHORT).show()
        }
    }

    fun getUserData() {
        val gameItems = arrayListOf<GameItem>()
        gameInfo = firebaseDB.getDataFromDB()
        for (i in gameInfo.indices) {
            gameItems.add(GameItem(gameInfo[i].image, gameInfo[i].title))
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