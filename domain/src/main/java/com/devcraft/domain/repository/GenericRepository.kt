package com.devcraft.domain.repository

import com.devcraft.domain.model.GenericModel

interface GenericRepository {
    suspend fun getGeneric(
        onSusses: (List<GenericModel>?) -> Unit,
        onError: ((Exception) -> Unit?)? = null
    )

    suspend fun insertGeneric(
        generic: List<GenericModel>,
        onError: ((Exception) -> Unit?)? = null
    )
}