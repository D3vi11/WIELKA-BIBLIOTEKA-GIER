package com.example.greatgamelibrary

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class GameInfo(var snapshot: DataSnapshot){

    var title = snapshot.child("title").value.toString()
    var imageName = snapshot.child("image").value.toString()
    var data = snapshot.child("additionalInformation").child("data").value.toString()
    var rating = snapshot.child("additionalInformation").child("rating").value.toString()
    var userRating = snapshot.child("additionalInformation").child("userRating").value.toString()

}
