package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityAtmManagementBinding
import com.example.banco_jototo.pojo.Cliente

class AtmManagementActivity : AppCompatActivity() {

    /*
    * Tendremos que recuperar el cliente para verificar si es administrador
    * y mostrar el boton de gestion de cajeros
    *
    *
    * */

    private lateinit var binding: ActivityAtmManagementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm_management)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        println(cliente.getNombre())


    }
}