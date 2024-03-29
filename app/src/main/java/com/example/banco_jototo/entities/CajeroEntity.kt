package com.example.banco_jototo.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cajeros")
data class CajeroEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                        var direccion: String,
                        var latitud: Double,
                        var longuitud: Double,
                        var zoom: String = "") : Serializable
