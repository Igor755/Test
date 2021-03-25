package com.devcraft.domain.model


data class DataModel(
    val type : String?,
    val status : Int?,
    val data :  List<AllDataModel>?,
    val message : String?
)
