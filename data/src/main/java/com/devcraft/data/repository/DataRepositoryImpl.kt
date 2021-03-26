package com.devcraft.data.repository

import com.devcraft.data.api.data_source.DataNetSource
import com.devcraft.data.extensions.map
import com.devcraft.domain.model.DataModel
import com.devcraft.domain.repository.DataRepository
import java.lang.Exception

class DataRepositoryImpl(private val dataNetSource: DataNetSource) : DataRepository {
    override fun getData(onComplete: (DataModel) -> Unit, onError: (Exception) -> Unit) =
        dataNetSource.getAllData(onComplete, onError)

}