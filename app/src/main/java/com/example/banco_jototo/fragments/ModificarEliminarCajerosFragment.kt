package com.example.banco_jototo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.banco_jototo.R
import com.example.banco_jototo.bd.CajeroApplication
import com.example.banco_jototo.databinding.FragmentModificarEliminarCajerosBinding
import com.example.banco_jototo.entities.CajeroEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TIPO = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [ModificarEliminarCajerosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModificarEliminarCajerosFragment : Fragment() {

    private lateinit var binding: FragmentModificarEliminarCajerosBinding

    private var esDelete: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            esDelete = it.getBoolean(ARG_TIPO, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModificarEliminarCajerosBinding.inflate(inflater, container, false)

        binding.btnSaveAtm.setOnClickListener {

            var id = binding.etId.text.toString().trim().toLong()

            if (esDelete == true){

                val cajero = CajeroEntity(id = id, direccion = "", latitud = 0.0, longuitud = 0.0)

                Thread{
                    CajeroApplication.database.cajeroDao().deleteCajero(cajero)

                    // Cambiar por un dialogo
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Cajero eliminado", Toast.LENGTH_SHORT).show()
                    }

                    // Limpia los EditText en el hilo principal
                    activity?.runOnUiThread {
                        binding.etId.text.clear()
                        binding.etDireccion.text.clear()
                        binding.etLatitud.text.clear()
                        binding.etLongitud.text.clear()
                    }

                }.start()

            }else{

                var direccion = binding.etDireccion.text.toString().trim()
                var latitud = binding.etLatitud.text.toString().trim().toDouble()
                var longitud = binding.etLongitud.text.toString().trim().toDouble()

                val cajero = CajeroEntity(id = id, direccion = direccion, latitud = latitud, longuitud = longitud)

                Thread{
                    CajeroApplication.database.cajeroDao().updateCajero(cajero)

                    // Cambiar por un dialogo
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Cajero modificado", Toast.LENGTH_SHORT).show()
                    }

                    // Limpia los EditText en el hilo principal
                    activity?.runOnUiThread {
                        binding.etId.text.clear()
                        binding.etDireccion.text.clear()
                        binding.etLatitud.text.clear()
                        binding.etLongitud.text.clear()
                    }
                }.start()
            }

        }




        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(d: Boolean) =
            ModificarEliminarCajerosFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_TIPO, d)
                }
            }
    }
}