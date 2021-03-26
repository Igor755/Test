package com.devcraft.domain.model


data class AllDataModel(
    val id : Long,
    val title : String,
    val date : Long,
    val categories : List<CategoriesModel>)
