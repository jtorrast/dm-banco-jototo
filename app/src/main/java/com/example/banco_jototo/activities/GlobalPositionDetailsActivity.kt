package com.example.banco_jototo.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityGlobalPositionDetailsBinding
import com.example.banco_jototo.fragments.AccountsMovementsFragment
import com.example.banco_jototo.fragments.MovementsListener
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat

class GlobalPositionDetailsActivity : AppCompatActivity(), MovementsListener {

    private lateinit var binding: ActivityGlobalPositionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuentaCliente = intent.getSerializableExtra("Cuenta")

        val frgMovents = AccountsMovementsFragment.newInstance(cuentaCliente as Cuenta)

        supportFragmentManager.beginTransaction()
            .add(R.id.frgMovimiento, frgMovents).commit()

        frgMovents.setListener(this)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_all->{
                    Log.i("Bottom Navigation", "Todos los movimientos")
                    true
                }
                R.id.navigation_type0->{
                    Log.i("Bottom Navigation", "Todos TIPO 0")
                    true
                }
                R.id.navigation_type1->{
                    Log.i("Bottom Navigation", "Todos TIPO 1")
                    true
                }
                R.id.navigation_type2->{
                    Log.i("Bottom Navigation", "Todos TIPO 2")
                    true
                }

                else -> {false}
            }
        }
    }

    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        Log.i("Details Activity", movimiento.getDescripcion().toString())
        val dialogView = layoutInflater.inflate(R.layout.dialog_movement, null)

        val formateador = SimpleDateFormat("dd/MM/yyyy")

        val textoInfo = dialogView.findViewById<TextView>(R.id.textInfo)
        textoInfo.text = "Id: ${movimiento.getId()} \n" +
                            "Descripcion: ${movimiento.getDescripcion()} \n" +
                            "Fecha: ${formateador.format(movimiento.getFechaOperacion())}"

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setBackground(getDrawable(R.color.azul_oscuro_medio))
            .setPositiveButton(resources.getString(R.string.accept), DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()
            })
            .setCancelable(false)
            .show()
    }
}