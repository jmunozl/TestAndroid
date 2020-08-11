package com.jalak.testandroid.network


import com.jalak.testandroid.entities.UserDataItem
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers(): Call<List<UserDataItem>>
}