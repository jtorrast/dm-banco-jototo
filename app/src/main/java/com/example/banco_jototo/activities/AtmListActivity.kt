package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.adapters.AtmListAdapter
import com.example.banco_jototo.adapters.OnClickListenerCajero
import com.example.banco_jototo.database.CajeroApplication
import com.example.banco_jototo.databinding.ActivityAtmListBinding
import com.example.banco_jototo.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmListActivity : AppCompatActivity(), OnClickListenerCajero {

    private lateinit var binding: ActivityAtmListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: AtmListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupRecyclerView()

    }

    override fun onResume() {
        super.onResume()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = AtmListAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(this)

        // Obtén la referencia del RecyclerView desde el layout
        val recyclerView: RecyclerView = binding.rvListaAtm

        // Asigna el adaptador al RecyclerView
        recyclerView.adapter = mAdapter

        // Configura el LinearLayoutManager para el RecyclerView
        recyclerView.layoutManager = linearLayoutManager

        // Llamada a getCajeros para obtener datos y actualizar el adaptador
        getCajeros()
    }

    private fun getCajeros() {
        val queue = LinkedBlockingQueue<MutableList<CajeroEntity>>()
        Thread{
            val cajeros = CajeroApplication.database.cajeroDao().getAllCajeros()
            queue.add(cajeros)
        }.start()
        mAdapter.setCajeros(queue.take())
    }

    override fun onClick(cajeroEntity: CajeroEntity) {
        println("ATMList ${cajeroEntity.id}")
        val intent = Intent(this, AtmFormActivity::class.java)
        intent.putExtra("Cajero", cajeroEntity)
        startActivity(intent)
    }


}