package com.devcraft.data.entity.network

import com.google.gson.annotations.SerializedName

data class DataNet(
    @SerializedName("type")
    val type : String,
    @SerializedName("status")
    val status : Int,
    @SerializedName("data")
    val data :  List<AllDataNet>,
    @SerializedName("message")
    val message : String
)
