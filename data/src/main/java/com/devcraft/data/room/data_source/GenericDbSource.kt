package com.devcraft.data.room.data_source

import com.devcraft.data.entity.room.GenericEntity
import com.devcraft.data.room.daos.GenericDao

class GenericDbSource(private val genericDao: GenericDao) {
    suspend fun getGeneric(id: Long) = genericDao.getGeneric(id)
    suspend fun insertGenerics(generics: List<GenericEntity>) = genericDao.insertGenerics(generics)
}