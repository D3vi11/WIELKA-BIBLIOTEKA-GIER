package com.example.greatgamelibrary

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot

data class GameInfo(var snapshot: DataSnapshot){
    var title = snapshot.child("title").getValue().toString()
    var imageName = snapshot.child("image").getValue().toString()
    lateinit var image: Bitmap
    var data = snapshot.child("additionalInformation").child("data").getValue().toString()
    var rating = snapshot.child("additionalInformation").child("rating").getValue().toString()
    var userRating = snapshot.child("additionalInformation").child("userRating").getValue().toString()

}
