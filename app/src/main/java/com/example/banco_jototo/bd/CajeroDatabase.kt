package com.example.banco_jototo.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banco_jototo.dao.CajeroDAO
import com.example.banco_jototo.entities.CajeroEntity

@Database(entities = [CajeroEntity::class], version = 1)
abstract class CajeroDatabase: RoomDatabase() {

    abstract fun cajeroDao(): CajeroDAO

}