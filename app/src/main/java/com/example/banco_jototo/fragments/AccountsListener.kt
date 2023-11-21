package com.example.banco_jototo.fragments

import com.example.banco_jototo.pojo.Cuenta

interface AccountsListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}