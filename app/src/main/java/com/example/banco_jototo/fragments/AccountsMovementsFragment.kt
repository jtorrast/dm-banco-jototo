package com.example.banco_jototo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jototo.adapters.MovementsAdapter
import com.example.banco_jototo.adapters.OnClickListenerMovements
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.FragmentAccountsMovementsBinding
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CUENTA = "cuenta"
private const val ARG_TIPO = "tipo"


/**
 * A simple [Fragment] subclass.
 * Use the [AccountsMovementsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsMovementsFragment : Fragment(), OnClickListenerMovements {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var cuenta: Cuenta
    private var tipo : Int? = null
    private lateinit var listener: MovementsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta = it.getSerializable(ARG_CUENTA) as Cuenta
            tipo = it.getSerializable(ARG_TIPO) as Int
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

            if (tipo != null){
                movementsList = mbo.getMovimientosTipo(cuenta, tipo!!) as ArrayList<Movimiento>
            }

            movementsAdapter = MovementsAdapter(movementsList, this)
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
        fun newInstance(cuenta: Cuenta, tipo: Int) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cuenta)
                    putSerializable(ARG_TIPO, tipo)
                }
            }

        fun newInstance(cuenta: Cuenta) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cuenta)
                }
            }

    }
    fun setListener(listener: MovementsListener){
        this.listener = listener
    }

    override fun onClick(movimiento: Movimiento) {
        Log.i("Accounts Movements", movimiento.getDescripcion().toString())
        if (listener != null) {
            listener.onMovimientoSeleccionado(movimiento)
        }
    }

}