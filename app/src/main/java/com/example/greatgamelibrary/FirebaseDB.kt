package com.example.greatgamelibrary

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.BitmapFactory
import android.provider.CalendarContract.Instances
import android.util.Log
import android.view.View
import android.widget.Toast
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
    var gameImage: ArrayList<GameImage> = arrayListOf()

    fun getDataFromDB(): ArrayList<GameInfo> {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in 0 until snapshot.childrenCount) {
                    gameDataList.add(GameInfo(snapshot.child(i.toString()).child("game")))
                    getDataFromStorage(gameDataList[i.toInt()].imageName, i.toInt())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }
        })
        return gameDataList
    }

    fun getDataFromStorage(imageName: String, item: Int) {
        var titleRef = storageReference.child("image/${imageName}")
        val ONE_MEGABYTE: Long = 1024 * 1024
        titleRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            gameImage.add(GameImage(BitmapFactory.decodeByteArray(it, 0, it.size)))
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}


