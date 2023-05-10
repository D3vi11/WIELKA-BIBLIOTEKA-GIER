package com.example.greatgamelibrary.data

data class SearchParameters(var title: String = "title", var date: String = "date", var rating: String = "rating", var userRating: String = "userRating"){

    fun getList():ArrayList<String>{
        return arrayListOf(title,date,rating,userRating)
    }
}
