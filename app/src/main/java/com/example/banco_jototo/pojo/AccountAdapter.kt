package com.example.banco_jototo.pojo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            binding.numAccountRV.text = account.getNumeroCuenta()
            binding.balanceRV.text = account.getSaldoActual().toString()
        }
    }

    override fun getItemCount(): Int = accounts.size

}