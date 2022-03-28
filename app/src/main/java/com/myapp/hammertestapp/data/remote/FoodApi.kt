package com.myapp.hammertestapp.data.remote

import com.myapp.hammertestapp.domain.model.MenuItem
import retrofit2.http.GET

interface FoodApi {

    @GET("/api/json/v1/1/search.php?f=a")
    suspend fun getListOfFood(): MenuItem
}