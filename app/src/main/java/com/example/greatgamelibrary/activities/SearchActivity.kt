package com.example.greatgamelibrary.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.R
import com.example.greatgamelibrary.adapters.SearchAdapter
import com.example.greatgamelibrary.data.SearchParameters

class SearchActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var searchButton: Button
    lateinit var mainMenuButton: Button
    var isLoggedIn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchButton = findViewById(R.id.SearchButton)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        setRecyclerView()
        isLoggedIn = intent.getBooleanExtra("isLoggedIn",false)
        searchButton.setOnClickListener {
            search()
        }
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@SearchActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",isLoggedIn)
            startActivity(intent)
        }
    }

    fun search(){

    }
    fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        fillRecyclerView()
    }

    fun fillRecyclerView(){
        var adapter = SearchAdapter(SearchParameters().getList())
        recyclerView.adapter = adapter
    }
}