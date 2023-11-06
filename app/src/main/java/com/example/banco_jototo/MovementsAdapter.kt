package com.example.banco_jototo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.databinding.ItemAccountGbBinding
import com.example.banco_jototo.pojo.Movimiento

class MovementsAdapter (private val movements: ArrayList<Movimiento>):
        RecyclerView.Adapter<MovementsAdapter.ViewHolder>(){

            private lateinit var context: Context

            inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
                val binding = ItemAccountGbBinding.bind(view)

                //colocamos setListenner si hiciera falta

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_account_gb, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movement = movements.get(position)

        with(holder){
            binding.numAccountRV.text = movement.getDescripcion()
            binding.balanceRV.text = movement.getImporte().toString()
        }
    }

}