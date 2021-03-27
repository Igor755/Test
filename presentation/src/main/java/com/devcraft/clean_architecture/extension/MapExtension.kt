package com.devcraft.clean_architecture.extension

import com.devcraft.clean_architecture.model.*
import com.devcraft.domain.model.*

fun List<GenericModel>.mapGeneric() = this.mapTo(mutableListOf(), {
    it.map()
})

fun GenericModel.map() = Generic(
    id = id
)

fun Data.map() = DataModel(
    type, status, data.mapNet(), message
)

fun DetailData.map() = DetailDataModel(
    id, title, date, categories.mapListNet()
)




fun Categories.map() = CategoriesModel(
    id, title, date, subCategories?.mapListModelCat()
)
fun List<Categories>.mapListNet() = mapTo(mutableListOf(), {
    it.map()
})
fun List<CategoriesModel>.mapListModel() = mapTo(mutableListOf(), {
    it.map()
})
fun CategoriesModel.map() =  Categories(
    id, title, date, subCategories?.mapListNetCat()
)











fun List<SubCategoriesModel>.mapListNetCat() = mapTo(mutableListOf(), {
    it.map()
})
fun List<SubCategories>.mapListModelCat() = mapTo(mutableListOf(), {
    it.map()
})
fun SubCategories.map() = SubCategoriesModel(
    id, title, date
)
fun SubCategoriesModel.map() = SubCategories(
    id, title, date
)







fun DataModel.map() = Data(
    type!!,status!!, data?.mapModel()!!,message!!
)

fun List<DetailDataModel>.mapModel() = mapTo(mutableListOf(), {
    it.map()
})

fun List<DetailData>.mapNet() = mapTo(mutableListOf(), {
    it.map()
})

fun DetailDataModel.map() = DetailData(
    id, title, date, categories.mapListModel()
)


