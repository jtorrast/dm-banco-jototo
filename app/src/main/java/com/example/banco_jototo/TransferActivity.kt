package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.graphics.toColor
import com.example.banco_jototo.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //datos spinner cuenta
        val datos = arrayOf("ES71-4857-6875-1234", "ES71-4857-6452-6423")

        val adapterOwnAccount = ArrayAdapter(this, R.layout.spinner_currency_item, datos)

        val spinnerOwnAccount = binding.spinnerAccount

        adapterOwnAccount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOwnAccount.adapter = adapterOwnAccount


        //datos spinner simbolo divisa
        val divisas = arrayOf("€", "$", "GBP")
        val adapterCurrency = ArrayAdapter(this, R.layout.spinner_currency_item, divisas)

        val spinnerCurrency = binding.spCurrency

        adapterCurrency.setDropDownViewResource(R.layout.spiner_drop_currency_item)

        spinnerCurrency.adapter = adapterCurrency



    }
}