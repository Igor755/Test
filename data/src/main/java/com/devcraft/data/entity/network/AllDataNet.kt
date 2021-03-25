package com.devcraft.data.entity.network

import com.google.gson.annotations.SerializedName

data class AllDataNet(
    @SerializedName("id")
    val id : Long,
    @SerializedName("title")
    val title : String,
    @SerializedName("date")
    val date : Long,
    @SerializedName("categories")
    val categories : List<CategoriesNet>)
