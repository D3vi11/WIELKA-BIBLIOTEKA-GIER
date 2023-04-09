package com.example.greatgamelibrary

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var searchButton: Button
    lateinit var advancedSearchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var gameItems: ArrayList<GameItem>
    lateinit var gameImage: ArrayList<Bitmap>
    lateinit var gameTitle: ArrayList<String>
    lateinit var gameItemAdapter: GameItemAdapter
    lateinit var context: Context
    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
        database = Firebase.database
        databaseReference = database.getReference("games")
        editText = findViewById(R.id.editText)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        advancedSearchButton = findViewById(R.id.advancedSearchButton)
        gameImage = arrayListOf()
        gameTitle = arrayListOf()
        gameItems = arrayListOf()
        gameItemAdapter = GameItemAdapter(gameItems)
        context = this
        searchButton.setOnClickListener {
            delete()
            getUserData()
        }
    }
    fun getUserData(){
        getTitleDataFromDB()
        getImageDataFromDB()
        if(gameTitle.size==gameImage.size){
            for(i in gameImage.indices){
                 gameItems.add(GameItem(gameImage[i], gameTitle[i]))
            }
        }
        gameItemAdapter = GameItemAdapter(gameItems)
        recyclerView.adapter = gameItemAdapter
    }
    fun delete(){
        gameItemAdapter.delete()
        recyclerView.adapter = gameItemAdapter
    }
    fun getTitleDataFromDB(){
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameTitle.clear()
                for(i in 0 until snapshot.childrenCount){
                    var title = snapshot.child(i.toString()).child("game").child("title")
                    gameTitle.add(title.getValue().toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG,"Failed to read value",error.toException())
            }
        })
    }
    fun getImageDataFromDB(){
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameImage.clear()
                for(i in 0 until snapshot.childrenCount){
                    var imageName = snapshot.child(i.toString()).child("game").child("image").getValue().toString()
                    var titleRef = storageReference.child("image/${imageName}")
                    val ONE_MEGABYTE: Long = 1024 * 1024
                    titleRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeByteArray(it,0,it.size)
                        gameImage.add(bitmap)
                    }.addOnFailureListener{
                        it.stackTrace
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG,"Failed to read value",error.toException())
            }
        })
    }
}