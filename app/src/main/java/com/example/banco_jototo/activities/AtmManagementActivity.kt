package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.R
import com.example.banco_jototo.bd.CajeroApplication
import com.example.banco_jototo.databinding.ActivityAtmManagementBinding
import com.example.banco_jototo.entities.CajeroEntity
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

        binding = ActivityAtmManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        println(cliente.getNombre())

        binding.btnAtmList.setOnClickListener {
            val intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }

        binding.btnAtmAdd.setOnClickListener {
            val intent = Intent(this, AtmFormActivity::class.java)
            startActivity(intent)
        }

        cargarDatosIniciales()



    }

    fun cargarDatosIniciales() {
        Thread {
            val cajeroDao = CajeroApplication.database.cajeroDao()

            // Verificar si ya hay datos en la base de datos
            val existingCajeros = cajeroDao.getAllCajeros()

            if (existingCajeros.isEmpty()) {
                // Si no hay datos, entonces inserta los datos iniciales
                val cajerosEntityList: List<CajeroEntity> = listOf(
                    CajeroEntity(1, "Carrer del Clariano, 1, 46021 Valencia, Valencia, España", 39.47600769999999, -0.3524475000000393, ""),
                    CajeroEntity(2, "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España", 39.4710366, -0.3547525000000178, ""),
                    CajeroEntity(3, "Av. del Port, 237, 46011 València, Valencia, España", 39.46161999999999, -0.3376299999999901, ""),
                    CajeroEntity(4, "Carrer del Batxiller, 6, 46010 València, Valencia, España", 39.4826729, -0.3639118999999482, ""),
                    CajeroEntity(5, "Av. del Regne de València, 2, 46005 València, Valencia, España", 39.4647669, -0.3732760000000326, "")
                )

                // Insertar datos solo si no existen previamente
                cajeroDao.insertAll(cajerosEntityList)
            }
        }.start()
    }
}