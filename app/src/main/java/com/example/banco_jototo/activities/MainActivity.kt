package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityMainBinding
import com.example.banco_jototo.fragments.MainActivityFragment
import com.example.banco_jototo.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        val frgMain = MainActivityFragment.newInstance(cliente)
        supportFragmentManager.beginTransaction()
            .add(R.id.frgMainActivity, frgMain).commit()
        
        /*Ahora tendremos que añadir los framents que llamemos desde el menu lateral*/
    }


}