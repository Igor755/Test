package com.devcraft.domain.interactor

import com.devcraft.domain.model.DataModel
import com.devcraft.domain.repository.DataRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DataInteractor :KoinComponent {

    private val dataRepository: DataRepository by inject()

    fun getData(onSuccess: (DataModel) -> Unit, onError: (Exception) -> Unit) {
        dataRepository.getData(onSuccess, onError)
    }
}