package com.devcraft.clean_architecture.extension

import com.devcraft.clean_architecture.model.Generic
import com.devcraft.domain.model.GenericModel

fun List<GenericModel>.mapGeneric() = this.mapTo(mutableListOf(), {
    it.map()
})

fun GenericModel.map() = Generic(
    id = id
)
