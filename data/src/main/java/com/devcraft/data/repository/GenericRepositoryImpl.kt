package com.devcraft.data.repository

import com.devcraft.data.api.data_source.GenericNetSource
import com.devcraft.data.extensions.mapGenericNet
import com.devcraft.data.extensions.mapToGenericEntity
import com.devcraft.data.room.data_source.GenericDbSource
import com.devcraft.domain.model.GenericModel
import com.devcraft.domain.repository.GenericRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GenericRepositoryImpl : GenericRepository, KoinComponent {
    private val categoriesNetSource: GenericNetSource by inject()
    private val categoriesDbSource: GenericDbSource by inject()

    override suspend fun getGeneric(
        onSuccess: (List<GenericModel>?) -> Unit,
        onError: ((Exception) -> Unit?)?
    ) {
        categoriesNetSource.getGeneric({
            onSuccess(it?.mapGenericNet()?.toList())
        }, {
            onError?.invoke(it)
        })
    }

    override suspend fun insertGeneric(
        generic: List<GenericModel>,
        onError: ((Exception) -> Unit?)?
    ) {
        try {
            categoriesDbSource.insertGenerics(generic.mapToGenericEntity())
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }

}