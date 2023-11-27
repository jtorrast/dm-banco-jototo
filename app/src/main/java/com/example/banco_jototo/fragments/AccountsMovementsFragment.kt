package com.example.banco_jototo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.R
import com.example.banco_jototo.adapters.MovementsAdapter
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.FragmentAccountsBinding
import com.example.banco_jototo.databinding.FragmentAccountsMovementsBinding
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CUENTA = "cuenta"


/**
 * A simple [Fragment] subclass.
 * Use the [AccountsMovementsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsMovementsFragment : Fragment() { //faltara implements listener
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var cuenta: Cuenta
    //faltara añadir la instanacia al listener correspodiente que tengamos que pasar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta = it.getSerializable(ARG_CUENTA) as Cuenta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        if (mbo != null){
            var movementsList: ArrayList<Movimiento> = mbo.getMovimientos(cuenta) as ArrayList<Movimiento>
            movementsAdapter = MovementsAdapter(movementsList)
            linearLayoutManager = LinearLayoutManager(context)
            itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

            binding.recyclerIdMovements.apply {
                layoutManager = linearLayoutManager
                adapter = movementsAdapter
                addItemDecoration(itemDecoration)
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountsMovementsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(cuenta: Cuenta) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cuenta)
                }
            }
    }
}