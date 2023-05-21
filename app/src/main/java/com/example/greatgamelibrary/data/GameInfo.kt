package com.example.greatgamelibrary.data

import com.google.firebase.database.DataSnapshot

data class GameInfo(var snapshot: DataSnapshot){

    var title = snapshot.child("title").value.toString()
    var imageName = snapshot.child("image").value.toString()
    var date = snapshot.child("additionalInformation").child("date").value.toString()
    var rating = snapshot.child("additionalInformation").child("rating").value.toString()
    var userRating = snapshot.child("additionalInformation").child("userRating").value.toString()
    var audioName = snapshot.child("additionalInformation").child("audio").value.toString()
    var videoName = snapshot.child("additionalInformation").child("video").value.toString()

    fun getList():ArrayList<String>{
        return arrayListOf(title,date,rating,userRating)
    }

}
