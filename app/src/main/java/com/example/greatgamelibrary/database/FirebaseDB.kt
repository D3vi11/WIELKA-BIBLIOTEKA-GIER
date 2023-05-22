package com.example.greatgamelibrary.database

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.greatgamelibrary.data.GameAudio
import com.example.greatgamelibrary.data.GameImage
import com.example.greatgamelibrary.data.GameInfo
import com.example.greatgamelibrary.data.GameVideo
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
    var gameVideo: ArrayList<GameVideo> = arrayListOf()

    fun getDataFromDB(): ArrayList<GameInfo> {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameImage = initializeArray(snapshot.childrenCount.toInt())
                for (i in 0 until snapshot.childrenCount) {
                    gameDataList.add(GameInfo(snapshot.child(i.toString())))
                    getDataFromStorage(gameDataList[i.toInt()].imageName, i.toInt())
                    getAudioFromStorage(gameDataList[i.toInt()].audioName)
                    getVideoFromStorage(gameDataList[i.toInt()].videoName)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value", error.toException())
            }
        })
        return gameDataList
    }

    fun getDataFromStorage(imageName: String, index: Int) {
        var imageRef = storageReference.child("image/${imageName}")
        val ONE_MEGABYTE: Long = 1024 * 1024

        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            gameImage.add(index,GameImage(BitmapFactory.decodeByteArray(it, 0, it.size)))
            if(index+1<=gameImage.size){
                gameImage.removeAt(index+1)
            }else{
                gameImage.removeAt(index-1)
            }
            activity.onUpdate()
        }.addOnFailureListener {
            it.stackTrace
        }

    }
    fun getAudioFromStorage(audioName: String){
        var audioRef = storageReference.child("audio/${audioName}")
        val audioUri = audioRef.downloadUrl
        audioUri.addOnSuccessListener {
            gameAudio.add(GameAudio(audioUri.result))
            activity.onUpdate()
        }.addOnFailureListener{
            it.stackTrace
        }
    }

    fun getVideoFromStorage(videoName: String){
        var videoRef = storageReference.child("video/${videoName}")
        val videoUri = videoRef.downloadUrl
        videoUri.addOnSuccessListener {
            gameVideo.add(GameVideo(videoUri.result))
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


