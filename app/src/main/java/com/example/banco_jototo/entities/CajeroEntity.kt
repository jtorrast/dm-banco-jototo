package com.example.banco_jototo.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cajeros")
data class CajeroEntity(@PrimaryKey(autoGenerate = true) var id: Int,
                        var direccion: String,
                        var latitud: Double,
                        var longuitud: Double,
                        var zoom: String)
