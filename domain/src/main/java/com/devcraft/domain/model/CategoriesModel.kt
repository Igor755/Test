package com.devcraft.domain.model


data class CategoriesModel(
    val id : Long,
    val title : String,
    val date : Long,
    val subCategories: List<SubCategoriesModel>?
)
