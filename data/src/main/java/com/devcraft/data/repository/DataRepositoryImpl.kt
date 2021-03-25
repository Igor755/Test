package com.devcraft.data.repository

import com.devcraft.data.api.data_source.DataNetSource
import com.devcraft.data.extensions.map
import com.devcraft.domain.model.DataModel
import com.devcraft.domain.repository.DataRepository
import java.lang.Exception

class DataRepositoryImpl(private val dataNetSource: DataNetSource) : DataRepository {
    override fun getData(onSuccess: (DataModel) -> Unit, onError: (Exception) -> Unit) {
        dataNetSource.getAllData({
            onSuccess(it.map())
        }, onError)
    }
}