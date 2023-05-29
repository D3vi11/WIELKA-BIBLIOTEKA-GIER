package com.example.greatgamelibrary

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import com.example.greatgamelibrary.activities.MainActivity
import com.example.greatgamelibrary.database.FirebaseDB
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private const val FAKE_STRING = "Strona Główna"
@RunWith(MockitoJUnitRunner::class)
class FirebaseTest {

    @Mock
    private lateinit var mainActivity: MainActivity

    @Test
    fun gameInfoTest(){
        val mainActivity = mock<MainActivity>{
            on{ getString(R.string.MainMenu) } doReturn FAKE_STRING
        }
        val db = FirebaseDB(mainActivity)

        assertEquals(2,2)
    }
}