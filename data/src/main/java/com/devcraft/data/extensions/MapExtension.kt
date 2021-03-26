package com.devcraft.data.extensions

import com.devcraft.data.entity.network.*
import com.devcraft.data.entity.room.GenericEntity
import com.devcraft.domain.model.*

fun List<GenericNet>.mapGenericNet() = this.mapTo(
    mutableListOf(), {
        it.map()
    }
)

fun GenericEntity.map() = GenericModel(id)

fun GenericNet.map() = GenericModel(id)

fun List<GenericEntity>.mapGenericEntity() = mapTo(
    mutableListOf(), {
        it.map()
    }
)

fun List<GenericModel>.mapToGenericEntity() = mapTo(mutableListOf(), {
    it.map()
})

fun GenericModel.map() = GenericEntity(id)

fun DataNet.map() = DataModel(
    type,status,data.mapNet(),message
)

fun AllDataNet.map() = AllDataModel(
    id, title, date, categories.mapListNet()
)

fun CategoriesNet.map() =  CategoriesModel(
    id, title, date, subCategories?.mapListModelCat()
)

fun SubCategoriesNet.map() = SubCategoriesModel(
    id, title, date
)

fun List<CategoriesNet>.mapListNet() = mapTo(mutableListOf(), {
    it.map()
})

fun List<CategoriesModel>.mapListModel() = mapTo(mutableListOf(), {
    it.map()
})


fun List<SubCategoriesModel>.mapListNetCat() = mapTo(mutableListOf(), {
    it.map()
})

fun List<SubCategoriesNet>.mapListModelCat() = mapTo(mutableListOf(), {
    it.map()
})

fun DataModel.map() = DataNet(
    type!!,status!!,data!!.mapModel(),message!!
)

fun List<AllDataModel>.mapModel() = mapTo(mutableListOf(), {
    it.map()
})

fun List<AllDataNet>.mapNet() = mapTo(mutableListOf(), {
    it.map()
})

fun AllDataModel.map() = AllDataNet(
    id, title, date, categories.mapListModel()
)

fun CategoriesModel.map() =  CategoriesNet(
    id, title, date, subCategories?.mapListNetCat()
)

fun SubCategoriesModel.map() = SubCategoriesNet(
    id, title, date
)


