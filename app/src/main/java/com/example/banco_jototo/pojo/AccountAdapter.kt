package com.example.banco_jototo.pojo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ItemAccountGbBinding


class AccountAdapter(private val accounts : List<Cuenta>):
    RecyclerView.Adapter<AccountAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemAccountGbBinding.bind(view)

        //colocamos setListenner si hiciera falta
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(com.example.banco_jototo.R.layout.item_account_gb, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountAdapter.ViewHolder, position: Int) {
        val account = accounts.get(position)

        with(holder){
            //colcar listener si hace falta
            var numCuentaCompleto = " ${account.getBanco()} - ${account.getSucursal()} - ${account.getDc()} - ${account.getNumeroCuenta()} "
            binding.numAccountRV.text = numCuentaCompleto

            if (account.getSaldoActual()!! < 0){
                binding.balanceRV.setTextColor(ContextCompat.getColor(context, R.color.red_error))

            }else{
                binding.balanceRV.setTextColor(ContextCompat.getColor(context, R.color.azul_electrico_palido))
            }
            binding.balanceRV.text = account.getSaldoActual().toString()
        }
    }

    override fun getItemCount(): Int = accounts.size

}