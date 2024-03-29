package com.example.banco_jototo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.banco_jototo.R
import com.example.banco_jototo.database.CajeroApplication
import com.example.banco_jototo.databinding.FragmentAddCajeroBinding
import com.example.banco_jototo.entities.CajeroEntity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddCajeroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddCajeroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCajeroBinding.inflate(inflater, container, false)

        binding.btnSaveAtm.setOnClickListener {
            var direccion = binding.etDireccion.text.toString().trim()
            var latitud: Double = binding.etLatitud.text.toString().trim().toDouble()
            var longuitud: Double = binding.etLongitud.text.toString().trim().toDouble()

            val cajero = CajeroEntity(direccion = direccion, latitud = latitud, longuitud = longuitud);

            Thread{
                CajeroApplication.database.cajeroDao().addCajero(cajero)

                // Muestra el Toast en el hilo principal
                activity?.runOnUiThread {
                    Toast.makeText(context, R.string.toast_add, Toast.LENGTH_SHORT).show()
                }

                // Limpia los EditText en el hilo principal
                activity?.runOnUiThread {
                    binding.etDireccion.text.clear()
                    binding.etLatitud.text.clear()
                    binding.etLongitud.text.clear()
                }


            }.start()

        }

        binding.btnCancelAtm.setOnClickListener {
            binding.etDireccion.text.clear()
            binding.etLatitud.text.clear()
            binding.etLongitud.text.clear()
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
         * @return A new instance of fragment AddCajeroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCajeroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}