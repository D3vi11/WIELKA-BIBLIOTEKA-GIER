package com.example.greatgamelibrary.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.R
import com.example.greatgamelibrary.adapters.GameItemAdapter
import com.example.greatgamelibrary.data.GameItem
import com.example.greatgamelibrary.database.FirebaseDB
import com.example.greatgamelibrary.interfaces.ActivityInterface

class MainActivity : AppCompatActivity(), ActivityInterface {
    lateinit var editText: EditText
    lateinit var searchButton: Button
    lateinit var advancedSearchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var firebaseDB: FirebaseDB
    var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseDB = FirebaseDB(this@MainActivity)
        editText = findViewById(R.id.editText)
        searchButton = findViewById(R.id.searchButton)
        advancedSearchButton = findViewById(R.id.advancedSearchButton)
        setRecyclerView()
        isLoggedIn = intent.getBooleanExtra("isLoggedIn",false)
        firebaseDB.getDataFromDB()
        searchButton.setOnClickListener {

        }
        advancedSearchButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            intent.putExtra("isLoggedIn",isLoggedIn)
            startActivity(intent)
        }
        searchButton.setOnClickListener {
            for(i in firebaseDB.gameImage.indices){
                println("Number: "+i+"image : "+firebaseDB.gameImage[i].image.byteCount)
            }
        }
    }

    override fun onUpdate() {
        getUserData()
    }


    fun getUserData() {
        var gameInfo = firebaseDB.gameDataList
        var gameItems = arrayListOf<GameItem>()
        var gameImage = firebaseDB.gameImage
        if(gameInfo.size == gameImage.size){
            for (i in gameInfo.indices) {
                gameItems.add(GameItem(gameImage[i].image, gameInfo[i].title))
            }
        }
        var adapter = GameItemAdapter(gameItems)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : GameItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, GameActivity::class.java)
                intent.putExtra("position",position)
                intent.putExtra("isLoggedIn",isLoggedIn)
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