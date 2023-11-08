package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.databinding.ActivityLottieBinding
import com.example.banco_jototo.pojo.Cliente

class LottieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLottieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val intent = Intent(this, MainActivity::class.java)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }
}