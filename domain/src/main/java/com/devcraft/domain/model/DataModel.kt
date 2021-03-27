package com.devcraft.domain.model


data class DataModel(
    val type : String?,
    val status : Int?,
    val data :  List<DetailDataModel>?,
    val message : String?
)
