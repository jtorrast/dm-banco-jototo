package com.example.banco_jototo.adapters

import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

interface OnClickListenerMovements {
    fun onClick(movimiento: Movimiento)
}