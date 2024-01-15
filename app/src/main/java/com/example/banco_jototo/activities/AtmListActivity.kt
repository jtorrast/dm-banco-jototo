package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.AtmListAdapter
import com.example.banco_jototo.adapters.OnClickListenerCajero
import com.example.banco_jototo.bd.CajeroApplication
import com.example.banco_jototo.databinding.ActivityAtmListBinding
import com.example.banco_jototo.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmListActivity : AppCompatActivity(), OnClickListenerCajero {

    private lateinit var binding: ActivityAtmListBinding
    private lateinit var mAdapter: AtmListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        mAdapter = AtmListAdapter(mutableListOf(), this)
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
        println("Click cajero ${cajeroEntity.id}")
    }
}