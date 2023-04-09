package com.example.greatgamelibrary

import android.content.ContentValues
import android.graphics.Bitmap
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
    var gameTitleArrayList: ArrayList<String> = arrayListOf()
    var gameImageArrayList: ArrayList<Bitmap> = arrayListOf()

    /**
     * Method gets All titles from DB
     * return ArrayList<String> of titles
     */
    fun getTitleDataFromDB(): ArrayList<String> {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameTitleArrayList.clear()
                for (i in 0 until snapshot.childrenCount) {
                    var title = snapshot.child(i.toString()).child("game").child("title")
                    gameTitleArrayList.add(title.getValue().toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }
        })
        return gameTitleArrayList
    }

    /**
     * Method gets All images from DB
     * return ArrayList<Bitmap> of Images
     */
    fun getImageDataFromDB(): ArrayList<Bitmap> {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameImageArrayList.clear()
                for (i in 0 until snapshot.childrenCount) {
                    var imageName =
                        snapshot.child(i.toString()).child("game").child("image").getValue()
                            .toString()
                    var titleRef = storageReference.child("image/${imageName}")
                    val ONE_MEGABYTE: Long = 1024 * 1024
                    titleRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        gameImageArrayList.add(bitmap)
                    }.addOnFailureListener {
                        it.stackTrace
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }
        })
        return gameImageArrayList
    }
}