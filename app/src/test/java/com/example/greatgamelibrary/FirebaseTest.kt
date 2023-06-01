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
import com.google.firebase.FirebaseApp

private const val FAKE_STRING = "Strona Główna"
@RunWith(MockitoJUnitRunner::class)
class FirebaseTest {

    @Mock
    private lateinit var activityContext: ActivityInterface

    @Test
    fun gameInfoTest(){
        activityContext = mock{

        }
        val db = FirebaseDB(activityContext)

        assertEquals(2,2)
    }
}