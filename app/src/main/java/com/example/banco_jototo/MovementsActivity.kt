package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityMovementsBinding
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta

class MovementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovementsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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





    }
}