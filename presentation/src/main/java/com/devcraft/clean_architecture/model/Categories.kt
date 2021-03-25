package com.devcraft.clean_architecture.model


data class Categories(
    val id : Long,
    val title : String,
    val date : Long,
    val subCategories: List<SubCategories>
)
