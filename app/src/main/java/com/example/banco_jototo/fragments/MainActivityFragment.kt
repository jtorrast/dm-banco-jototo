package com.example.banco_jototo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banco_jototo.R
import com.example.banco_jototo.activities.ChangePasswordActivity2
import com.example.banco_jototo.activities.GlobalPositionActivity
import com.example.banco_jototo.activities.MovementsActivity
import com.example.banco_jototo.activities.TransferActivity
import com.example.banco_jototo.databinding.FragmentMainActivityBinding
import com.example.banco_jototo.pojo.Cliente

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CLIENTE = "Cliente"


/**
 * A simple [Fragment] subclass.
 * Use the [MainActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainActivityFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentMainActivityBinding
    private lateinit var cliente: Cliente

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

        binding = FragmentMainActivityBinding.inflate(inflater, container, false)

        binding.viewDni.text = cliente.getNombre()

        binding.btnTransfers.setOnClickListener {
            val intent = Intent(context, TransferActivity::class.java)
            startActivity(intent)
        }

        binding.btnChangePass.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity2::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnGlobalPosition.setOnClickListener {
            val intent = Intent(context, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnMovements.setOnClickListener {
            val intent = Intent(context, MovementsActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            //finishAffinity()
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
         * @return A new instance of fragment MainActivityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(c: Cliente) =
            MainActivityFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                }
            }
    }
}