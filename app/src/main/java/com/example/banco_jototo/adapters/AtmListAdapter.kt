package com.example.banco_jototo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ItemAtmBinding
import com.example.banco_jototo.entities.CajeroEntity

class AtmListAdapter(private var cajeros: MutableList<CajeroEntity>, private var listener: OnClickListenerCajero): RecyclerView.Adapter<AtmListAdapter.ViewHoler>() {

    private lateinit var mContext: Context
    inner class ViewHoler(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemAtmBinding.bind(view)

        fun setListener(cajeroEntity: CajeroEntity){
            binding.root.setOnClickListener {
                listener.onClick(cajeroEntity)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_atm, parent, false)
        return ViewHoler(view)
    }

    override fun getItemCount(): Int = cajeros.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val cajero = cajeros.get(position)

        with(holder){
            binding.tvAtmNum.text = "ATM ${cajero.id.toString()}"
            binding.tvAtmDir.text = cajero.direccion
        }
    }

    fun add(cajeroEntity: CajeroEntity){
        cajeros.add(cajeroEntity)
        notifyDataSetChanged()
    }

    fun setCajeros(cajero: MutableList<CajeroEntity>){
        this.cajeros = cajeros
        notifyDataSetChanged()
    }



}