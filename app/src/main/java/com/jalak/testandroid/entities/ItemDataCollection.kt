package com.jalak.testandroid.entities


import com.google.gson.annotations.SerializedName

class ItemDataCollection

data class ItemDataItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("responseCode")
    val responseCode: Int,
    @SerializedName("result")
    val result: Result
)

data class Result(
    @SerializedName("items")
    val items: List<Item>
)

data class Item(
    @SerializedName("detail")
    val detail: Detail,
    @SerializedName("name")
    val name: String
)

data class Detail(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)