package com.example.greatgamelibrary

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseDB() {
    var storageReference: StorageReference = FirebaseStorage.getInstance().reference
    var databaseReference: DatabaseReference = Firebase.database.getReference("games")
    var gameDataList: ArrayList<GameInfo> = arrayListOf()

    fun getDataFromDB(): ArrayList<GameInfo>{
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in 0 until snapshot.childrenCount){
                    gameDataList.add(GameInfo(snapshot.child(i.toString()).child("game")))
                    getDataFromStorage(gameDataList.get(i.toString().toInt()).imageName,i.toString().toInt())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }

        })
        return gameDataList
    }

    fun getDataFromStorage(imageName: String,index: Int){
        var titleRef = storageReference.child("image/${imageName}")
        val ONE_MEGABYTE: Long = 1024 * 1024
        titleRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            gameDataList.get(index).image = BitmapFactory.decodeByteArray(it, 0, it.size)
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}