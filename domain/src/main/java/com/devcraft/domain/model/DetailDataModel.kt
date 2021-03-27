package com.devcraft.domain.model


data class DetailDataModel(
    val id : Long,
    val title : String,
    val date : Long,
    val categories : List<CategoriesModel>)
