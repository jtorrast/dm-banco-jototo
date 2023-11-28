package com.example.banco_jototo.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.AccountAdapter
import com.example.banco_jototo.adapters.OnClickListener
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityGlobalPositionBinding
import com.example.banco_jototo.fragments.AccountsFragment
import com.example.banco_jototo.fragments.AccountsListener
import com.example.banco_jototo.fragments.AccountsMovementsFragment
import com.example.banco_jototo.fragments.MovementsListener
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat

class GlobalPositionActivity : AppCompatActivity(), AccountsListener, MovementsListener {

    private lateinit var binding: ActivityGlobalPositionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Recuperamos el cliente*/
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        Log.e("CLIENTE GLOBAL POSITION", cliente.getNombre()!!)

        val frgAccounts = AccountsFragment.newInstance(cliente as Cliente)

        supportFragmentManager.beginTransaction()
            .add(R.id.frgCuentas, frgAccounts).commit()

        /*PROBAR ESTATICO HAY QUE AÑADIR AL XML DEL LAYOUT Y LA CLASS*/
        /*var frgAccounts: AccountsFragment = AccountsFragment.newInstance(cliente)
        frgAccounts = supportFragmentManager.findFragmentById(R.id.frgCuentas) as AccountsFragment*/

        frgAccounts.setListener(this)

    }
    /*override fun onClick(obj: Any) {
        val frgMovements = AccountsMovementsFragment.newInstance(obj as Cuenta)


        Log.i("Configuración de pantalla", "Valor de screenLayout: ${resources.configuration.screenLayout}")

        //el valor 268435796 equivale a la pantalla de la tablet
        if (resources.configuration.screenLayout == 268435796){
            Log.i("Dispositivo", "tablet")

            //tablet
                supportFragmentManager.beginTransaction().
                replace(R.id.frgMovimiento, frgMovements).commit()

        }else{
            //Pantalla movil, cambiamos actividad
            val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
            intent.putExtra("Cuenta", obj as Cuenta)
            startActivity(intent)
        }

    }*/

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        val frgMovements = AccountsMovementsFragment.newInstance(cuenta)


        Log.i("Configuración de pantalla", "Valor de screenLayout: ${resources.configuration.screenLayout}")

        //el valor 268435796 equivale a la pantalla de la tablet
        if (resources.configuration.screenLayout == 268435796){
            Log.i("Dispositivo", "tablet")
            frgMovements.setListener(this)

            //tablet
            supportFragmentManager.beginTransaction().
            replace(R.id.frgMovimiento, frgMovements).commit()

        }else{
            //Pantalla movil, cambiamos actividad
            val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
            intent.putExtra("Cuenta", cuenta)
            startActivity(intent)
        }
    }

    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        Log.i("Global Position", movimiento.getDescripcion().toString())
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