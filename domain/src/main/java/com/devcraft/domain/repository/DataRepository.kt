package com.devcraft.domain.repository

import com.devcraft.domain.model.DataModel
import java.lang.Exception

interface DataRepository {
    fun getData(onComplete: (DataModel) -> Unit, onError: (Exception) -> Unit)
}