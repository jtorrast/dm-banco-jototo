package com.example.banco_jototo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.banco_jototo.entities.CajeroEntity

@Dao
interface CajeroDAO {
    @Query("SELECT * FROM cajeros")
    fun getAllCajeros(): MutableList<CajeroEntity>

    @Insert
    fun insertAll(CajeroEntityList: List<CajeroEntity>)

    @Insert
    fun addCajero(cajeroEntity: CajeroEntity)

    @Update
    fun updateCajero(cajeroEntity: CajeroEntity)

    @Delete
    fun deleteCajero(cajeroEntity: CajeroEntity)

}