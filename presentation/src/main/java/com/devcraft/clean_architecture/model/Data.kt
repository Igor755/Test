package com.devcraft.clean_architecture.model

import com.devcraft.domain.model.AllDataModel


data class Data(
    val type : String,
    val status : Int,
    val data :  List<AllDataModel>,
    val message : String
)
