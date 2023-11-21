package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.AccountAdapter
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityGlobalPositionBinding
import com.example.banco_jototo.fragments.AccountsFragment
import com.example.banco_jototo.fragments.AccountsListener
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), AccountsListener {

    private lateinit var binding: ActivityGlobalPositionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Recuperamos la lista de cuentas y la pasamos al adaptaer*/
        /*val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val listaCuentas: List<Cuenta> = mbo?.getCuentas(cliente) as List<Cuenta>*/

        val frgCuentas : AccountsFragment = supportFragmentManager.findFragmentById(R.id.frgCuentas) as AccountsFragment
        frgCuentas.setCuentasListener(this)



    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        TODO("Not yet implemented")
    }
}