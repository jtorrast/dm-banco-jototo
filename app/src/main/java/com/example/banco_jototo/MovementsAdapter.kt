package com.example.banco_jototo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.databinding.ItemAccountGbBinding
import com.example.banco_jototo.databinding.ItemMovementsBinding
import com.example.banco_jototo.pojo.Movimiento
import java.text.SimpleDateFormat

class MovementsAdapter (private val movements: ArrayList<Movimiento>):
        RecyclerView.Adapter<MovementsAdapter.ViewHolder>(){

            private lateinit var context: Context

            inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
                val binding = ItemMovementsBinding.bind(view)

                //colocamos setListenner si hiciera falta

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movements, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movement = movements.get(position)

        with(holder){
            val formateador = SimpleDateFormat("dd/MM/yyyy")
            var dataInfo = "${formateador.format(movement.getFechaOperacion())} Importe ${movement.getImporte()}"
            binding.rwMovement.text = movement.getDescripcion()
            binding.rwDataAmount.text = dataInfo
        }
    }

}