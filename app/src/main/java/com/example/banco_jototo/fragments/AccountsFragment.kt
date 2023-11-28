package com.example.banco_jototo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.AccountAdapter
import com.example.banco_jototo.adapters.OnClickListener
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.FragmentAccountsBinding
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta

private const val ARG_CLIENTE = "Cliente"
class AccountsFragment: Fragment(), OnClickListener {

    private lateinit var binding: FragmentAccountsBinding
    private lateinit var accountsAdapter: AccountAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var cliente: Cliente
    private lateinit var listener: AccountsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
            Log.e("Cliente INICIALIZADO", cliente.getNombre().toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountsBinding.inflate(inflater, container, false)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        var listaCuentas: List<Cuenta>? = mbo?.getCuentas(cliente as Cliente?) as List<Cuenta>?

        if (listaCuentas != null) {
            accountsAdapter = AccountAdapter(listaCuentas, this)
        }

        linearLayoutManager = LinearLayoutManager(context)


        binding.recyclerIdAccounts.apply {
            layoutManager = linearLayoutManager
            adapter = accountsAdapter

        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_accounts, container, false)
    }


    override fun onClick(cuenta: Cuenta) {
        if (listener != null) {
            listener.onCuentaSeleccionada(cuenta)
        }
    }

    fun setListener(listener: AccountsListener){
        this.listener = listener
    }


    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                    Log.e("NEW INSTANCE", c.getNombre()!!)
                }
            }
    }



}