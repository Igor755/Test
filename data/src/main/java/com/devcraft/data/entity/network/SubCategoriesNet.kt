package com.devcraft.data.entity.network

import com.google.gson.annotations.SerializedName

data class SubCategoriesNet(
    @SerializedName("id")
    val id : Long,
    @SerializedName("title")
    val title : String,
    @SerializedName("date")
    val date : Long
)
