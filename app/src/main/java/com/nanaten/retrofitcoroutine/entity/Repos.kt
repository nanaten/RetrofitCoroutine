package com.nanaten.retrofitcoroutine.entity

import com.google.gson.annotations.SerializedName

data class Repos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)