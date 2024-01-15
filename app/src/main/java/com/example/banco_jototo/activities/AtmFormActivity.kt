package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityAtmFormBinding
import com.example.banco_jototo.fragments.AddCajeroFragment

class AtmFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.contenedorFragmentAtm, AddCajeroFragment()).commit()


    }
}