package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dni = intent.getStringExtra("dni")

        binding.viewDni.text = dni
    }
}