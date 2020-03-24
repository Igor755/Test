package com.devcraft.domain.interactor

import com.devcraft.domain.model.GenericModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class GenericInteractor : KoinComponent {
    private val repository: GenericInteractor by inject()
    suspend fun getGeneric(
        onSusses: (GenericModel) -> Unit,
        onError: ((Exception) -> Unit?)?
    ) {
        return repository.getGeneric(onSusses, onError)
    }

    suspend fun insertGeneric(
        generic: GenericModel,
        onError: ((Exception) -> Unit?)? = null
    ) {
        repository.insertGeneric(generic, onError = onError)
    }
}