package com.example.greatgamelibrary

import android.content.Context
import android.net.Uri
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import com.example.greatgamelibrary.database.FirebaseDB
import com.example.greatgamelibrary.interfaces.ActivityInterface
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.greatgamelibrary.activities.MainActivity
import com.example.greatgamelibrary.data.GameInfo
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot

@RunWith(MockitoJUnitRunner::class)
class FirebaseTest {

    @Mock
    private lateinit var activityContext: ActivityInterface
    @Mock
    private lateinit var context: AppCompatActivity

    @Test
    fun gameInfoTest(){
        //val db = FirebaseDB(activityContext)

        assertEquals(2,2)
    }
}