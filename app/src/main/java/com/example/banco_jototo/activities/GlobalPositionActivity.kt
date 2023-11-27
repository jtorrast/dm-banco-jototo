package com.example.banco_jototo.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

class GlobalPositionActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityGlobalPositionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Recuperamos la lista de cuentas y la pasamos al adaptaer*/
        //val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        /*val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val listaCuentas: List<Cuenta> = mbo?.getCuentas(cliente) as List<Cuenta>*/

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
    override fun onClick(obj: Any) {
        val frgMovements = AccountsMovementsFragment.newInstance(obj as Cuenta)

        if (resources.configuration.screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            Log.i("Dispositivo", "tablet")
            //tablet
                supportFragmentManager.beginTransaction().
                add(R.id.frgMovimiento, frgMovements).commit()
           /* if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //horizontal
            }else{

            }*/

        }else{
            //cambiamos actividad
            val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
            intent.putExtra("Cuenta", obj as Cuenta)

            startActivity(intent)
        }

    }

   /* override fun onCuentaSeleccionada(cuenta: Cuenta) {

        Log.e("Global Position ", cuenta.getSaldoActual().toString())

       if (cuenta != null) {
            var hayDetalle = supportFragmentManager.findFragmentById(R.id.frgMovimiento) != null

            if (hayDetalle) {
                var movementsFragment: AccountsMovementsFragment = AccountsMovementsFragment.newInstance(cuenta as Cuenta)
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frgMovimiento, movementsFragment)
                transaction.commitNow()


            }else{
                val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
                intent.putExtra("cuenta", cuenta)
                startActivity(intent)
            }
        }
    }*/


}