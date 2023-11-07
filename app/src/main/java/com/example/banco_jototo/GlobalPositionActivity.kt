package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityGlobalPositionBinding
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityGlobalPositionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Recuperamos la lista de cuentas y la pasamos al adaptaer*/
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val listaCuentas: List<Cuenta> = mbo?.getCuentas(cliente) as List<Cuenta>

        accountAdapter = AccountAdapter(listaCuentas)
        linearLayoutManager = LinearLayoutManager(this)

        binding.rwAccounts.apply {
            layoutManager = linearLayoutManager
            adapter = accountAdapter
        }

    }
}