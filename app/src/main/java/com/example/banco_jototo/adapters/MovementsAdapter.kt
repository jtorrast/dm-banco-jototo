package com.example.banco_jototo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ItemMovementsBinding
import com.example.banco_jototo.pojo.Movimiento
import java.text.SimpleDateFormat

class MovementsAdapter(private val movements: ArrayList<Movimiento>, private val listener: OnClickListenerMovements? = null):
        RecyclerView.Adapter<MovementsAdapter.ViewHolder>(){
            private lateinit var context: Context
            inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

                val binding = ItemMovementsBinding.bind(view)

                fun setListener(movimiento: Movimiento){
                    binding.root.setOnClickListener {
                        listener?.onClick(movimiento)
                    }
                }

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
            setListener(movement)
            val formateador = SimpleDateFormat("dd/MM/yyyy")
            binding.rwMovement.text = movement.getDescripcion()
            if (movement.getImporte()!! < 0){
                binding.rwDataAmount.setTextColor(ContextCompat.getColor(context, R.color.red_error))
            }else{
                binding.rwDataAmount.setTextColor(ContextCompat.getColor(context, R.color.azul_electrico_palido))
            }

            var dataInfo = "${formateador.format(movement.getFechaOperacion())} Importe ${movement.getImporte()}"
            binding.rwDataAmount.text = dataInfo
        }
    }

}