package com.example.greatgamelibrary

import com.example.greatgamelibrary.activities.MainActivity
import com.example.greatgamelibrary.data.GameInfo
import com.example.greatgamelibrary.database.FirebaseDB
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FirebaseTest {

    @Mock
    private lateinit var mainActivity: MainActivity

    @Test
    fun gameInfoTest(){
        var gameInfoTestArray: ArrayList<GameInfo> = arrayListOf()
        var firebase: FirebaseDB = FirebaseDB(mainActivity)
        var gameInfo: ArrayList<GameInfo> = firebase.getDataFromDB()
        assertEquals(gameInfo,gameInfoTestArray)
    }
}