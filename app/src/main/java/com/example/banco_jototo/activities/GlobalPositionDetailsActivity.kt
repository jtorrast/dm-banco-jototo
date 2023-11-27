package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityGlobalPositionDetailsBinding
import com.example.banco_jototo.fragments.AccountsMovementsFragment
import com.example.banco_jototo.pojo.Cuenta

class GlobalPositionDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuentaCliente = intent.getSerializableExtra("Cuenta")

        val frgMovents = AccountsMovementsFragment.newInstance(cuentaCliente as Cuenta)

        supportFragmentManager.beginTransaction()
            .add(R.id.frgMovimiento, frgMovents).commit()
    }
}