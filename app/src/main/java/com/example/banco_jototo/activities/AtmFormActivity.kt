package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityAtmFormBinding
import com.example.banco_jototo.fragments.AddCajeroFragment
import com.example.banco_jototo.fragments.ModificarEliminarCajerosFragment

class AtmFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar: androidx.appcompat.widget.Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction()
            .add(R.id.contenedorFragmentAtm, AddCajeroFragment()).commit()

        binding.toolbarTitle.text = getString(R.string.text_toolbar_add)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_atm_form,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_delete->{

                val frgDelete = ModificarEliminarCajerosFragment.newInstance(true)

                supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgDelete).commit()
                binding.toolbarTitle.text = getString(R.string.text_toolbar_delete)
                true
            }
            R.id.action_update->{
                val frgUpdate = ModificarEliminarCajerosFragment.newInstance(false)
                supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgUpdate).commit()
                binding.toolbarTitle.text = getString(R.string.text_toolbar_update)
                true
            }

            else -> {false}
        }
    }

}