package com.example.greatgamelibrary.database

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.greatgamelibrary.data.GameAudio
import com.example.greatgamelibrary.data.GameImage
import com.example.greatgamelibrary.data.GameInfo
import com.example.greatgamelibrary.interfaces.ActivityInterface
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseDB(var activity: ActivityInterface) {
    var storageReference: StorageReference = FirebaseStorage.getInstance().reference
    var databaseReference: DatabaseReference = Firebase.database.getReference("games")
    var gameDataList: ArrayList<GameInfo> = arrayListOf()
    var gameImage: ArrayList<GameImage> = arrayListOf()
    var gameAudio: ArrayList<GameAudio> = arrayListOf()

    fun getDataFromDB(): ArrayList<GameInfo> {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameImage = initializeArray(snapshot.childrenCount.toInt())
                for (i in 0 until snapshot.childrenCount) {
                    gameDataList.add(GameInfo(snapshot.child(i.toString()).child("game")))
                    getDataFromStorage(gameDataList[i.toInt()].imageName,gameDataList[i.toInt()].audioName,gameDataList[i.toInt()].videoName, i.toInt())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }
        })
        return gameDataList
    }

    fun getDataFromStorage(imageName: String, audioName: String, videoName: String, item: Int) {
        var imageRef = storageReference.child("image/${imageName}")
        var audioRef = storageReference.child("audio/${audioName}")
        var videoRef = storageReference.child("video/${videoName}")
        val ONE_MEGABYTE: Long = 1024 * 1024
        titleRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            gameImage.add(index,GameImage(BitmapFactory.decodeByteArray(it, 0, it.size)))
            if(index+1<=gameImage.size){
                gameImage.removeAt(index+1)
            }else{
                gameImage.removeAt(index-1)
            }
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            gameImage.add(GameImage(BitmapFactory.decodeByteArray(it, 0, it.size)))
            activity.onUpdate()
        }.addOnFailureListener {
            it.stackTrace
        }
        var uri = audioRef.downloadUrl

        uri.addOnSuccessListener {
            gameAudio.add(GameAudio(uri.result))
            activity.onUpdate()
        }.addOnFailureListener{
            it.stackTrace
        }


    }

    fun initializeArray(size: Int): ArrayList<GameImage>{
        var bitmap:Bitmap = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888)
        var gameImage = arrayListOf<GameImage>()
        for(i in 0 until size){
            gameImage.add(GameImage(bitmap))
        }
        println(gameImage.size)
        return gameImage
    }
}


