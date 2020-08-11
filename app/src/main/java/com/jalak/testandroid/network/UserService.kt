package com.jalak.testandroid.network


import com.jalak.testandroid.entities.Detail
import com.jalak.testandroid.entities.DetailUser
import com.jalak.testandroid.entities.UserDataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("users")
    fun setUsers(@Body user:Detail): Call<DetailUser>
}