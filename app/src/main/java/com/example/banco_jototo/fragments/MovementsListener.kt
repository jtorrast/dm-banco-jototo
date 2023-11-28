package com.example.banco_jototo.fragments

import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

interface MovementsListener {
    fun onMovimientoSeleccionado(movimiento: Movimiento)
}