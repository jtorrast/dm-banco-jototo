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

        //datos spinner
        val datos = arrayOf("ES71-4857-6875-1234", "ES71-4857-6452-6423")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)

        val spinner = binding.spinnerAccount

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter


    }
}