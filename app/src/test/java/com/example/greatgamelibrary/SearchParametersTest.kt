package com.example.greatgamelibrary

import com.example.greatgamelibrary.data.SearchParameters
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchParametersTest {


    @Test
    fun searchParametersTest() {
        val testList = arrayListOf("title","date","rating","userRating")
        val searchParameters = SearchParameters()
        val list = searchParameters.getList()
        assertEquals(list,testList)
    }
}