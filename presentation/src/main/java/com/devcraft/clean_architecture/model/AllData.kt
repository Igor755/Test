package com.devcraft.clean_architecture.model


data class AllData(
    val id : Long,
    val title : String,
    val date : Long,
    val categories : List<Categories>)
