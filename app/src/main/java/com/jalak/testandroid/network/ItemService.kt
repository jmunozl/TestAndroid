package com.jalak.testandroid.network

import com.jalak.testandroid.entities.ItemDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

    @GET("search?")
    fun getItem(@Query("rut")rut:String): Call<ItemDataItem>
}