package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.MovementsAdapter
import com.example.banco_jototo.adapters.OnClickListenerMovements
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityMovementsBinding
import com.example.banco_jototo.fragments.MovementsListener
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

class MovementsActivity : AppCompatActivity(){
    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMovementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //generamos el spinner con las cuentas del cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val accountList: List<Cuenta> = mbo?.getCuentas(cliente) as List<Cuenta>
        var datos = Array<String?>(accountList.size) {null}

        for((index,account) in accountList.withIndex()){
            datos[index] = " ${account.getBanco()} - ${account.getSucursal()} - ${account.getDc()} - ${account.getNumeroCuenta()} "

        }

        val adapterOwnAccount = ArrayAdapter(this, R.layout.spinner_item, datos)

        val spinnerOwnAccount = binding.spinnerMovements
        adapterOwnAccount.setDropDownViewResource(R.layout.spiner_drop_item)
        spinnerOwnAccount.adapter = adapterOwnAccount

        //mostramos los movimientos de la cuenta seleccionada

        binding.spinnerMovements.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                var listaMovimientos: ArrayList<Movimiento> = mbo?.getMovimientos(accountList.get(position)) as ArrayList<Movimiento>

                val context = binding.root.context
                movementsAdapter = MovementsAdapter(listaMovimientos)
                linearLayoutManager = LinearLayoutManager(context)


                binding.rwMovements.apply {
                    layoutManager = linearLayoutManager
                    adapter = movementsAdapter

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }



}