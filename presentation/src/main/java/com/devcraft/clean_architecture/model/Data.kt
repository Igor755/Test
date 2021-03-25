package com.devcraft.clean_architecture.model


data class Data(
    val type: String,
    val status: Int,
    val data: MutableList<AllData>,
    val message: String
)
