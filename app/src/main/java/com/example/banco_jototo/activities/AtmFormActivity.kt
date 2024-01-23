package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityAtmFormBinding
import com.example.banco_jototo.entities.CajeroEntity
import com.example.banco_jototo.fragments.AddCajeroFragment
import com.example.banco_jototo.fragments.ModificarEliminarCajerosFragment
import com.example.banco_jototo.pojo.Cliente

class AtmFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cajero = intent.getSerializableExtra("Cajero") as? CajeroEntity

        if (cajero == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contenedorFragmentAtm, AddCajeroFragment()).commit()
            binding.toolbarTitle.text = getString(R.string.text_toolbar_add)
        }else{
            val toolBar: androidx.appcompat.widget.Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            val frgUpdate = ModificarEliminarCajerosFragment.newInstance(false, cajero)
            supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgUpdate).commit()
            binding.toolbarTitle.text = getString(R.string.text_toolbar_update)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_atm_form,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val cajero = intent.getSerializableExtra("Cajero") as? CajeroEntity

        return when(item.itemId){
            R.id.action_delete->{

                var frgDelete = ModificarEliminarCajerosFragment.newInstance(true)

                if (cajero != null){
                    frgDelete = ModificarEliminarCajerosFragment.newInstance(true, cajero)
                }

                supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgDelete).commit()
                binding.toolbarTitle.text = getString(R.string.text_toolbar_delete)
                true
            }
            R.id.action_update->{

                var frgUpdate = ModificarEliminarCajerosFragment.newInstance(true)

                if (cajero != null){
                    frgUpdate = ModificarEliminarCajerosFragment.newInstance(true, cajero)
                }

                supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgUpdate).commit()
                binding.toolbarTitle.text = getString(R.string.text_toolbar_update)
                true
            }

            else -> {false}
        }
    }

}