package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.databinding.ActivityMovementsBinding

class MovementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovementsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}