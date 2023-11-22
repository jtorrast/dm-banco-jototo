package com.example.banco_jototo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.AccountAdapter
import com.example.banco_jototo.adapters.OnClickListener
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.FragmentAccountsBinding
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CLIENTE = "cliente"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsFragment : Fragment(), OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAccountsBinding
    private lateinit var accountsAdapter: AccountAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var cliente: Cliente
    private lateinit var listener: AccountsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        val listaCuentas: List<Cuenta>? = mbo?.getCuentas(cliente as Cliente) as List<Cuenta>?



        accountsAdapter = AccountAdapter(listaCuentas!!, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerIdAccounts.apply {
            layoutManager = linearLayoutManager
            adapter = accountsAdapter
            addItemDecoration(itemDecoration)
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                }
            }
    }

    fun setCuentasListener(listener: AccountsListener){
        this.listener = listener
    }

    override fun onClick(cuenta: Cuenta) {
        if (listener != null){
            listener.onCuentaSeleccionada(cuenta)
        }
    }
}