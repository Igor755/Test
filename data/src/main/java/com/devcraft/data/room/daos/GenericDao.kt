package com.devcraft.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devcraft.data.entity.room.GenericEntity

@Dao
abstract class GenericDao {

    @Query("SELECT * FROM genericentity WHERE id = :id")
    abstract suspend fun getGeneric(id: Long): GenericEntity?

    @Insert
    abstract suspend fun insertGenerics(generic: List<GenericEntity>)


}