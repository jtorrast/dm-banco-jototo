package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.banco_jototo.databinding.ActivityLottieBinding
import com.example.banco_jototo.pojo.Cliente

class LottieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLottieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //startActivity(intent)

        with(binding.animationLoad) {
            setMinAndMaxFrame(0, 200)
            animate().setDuration(1000).setStartDelay(100)
        }

        Handler().postDelayed({
            val cliente = intent.getSerializableExtra("Cliente") as Cliente
            // Realiza acciones después de un retraso
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
            finish()
        }, 5000)
    }
}