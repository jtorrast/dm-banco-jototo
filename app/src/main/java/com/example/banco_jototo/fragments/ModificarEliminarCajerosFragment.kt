package com.example.banco_jototo.fragments

import android.os.Bundle
import android.text.Editable
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
private const val ARG_CAJERO = "cajero"

class ModificarEliminarCajerosFragment : Fragment() {

    private lateinit var binding: FragmentModificarEliminarCajerosBinding

    private var esDelete: Boolean? = null
    private lateinit var cajero: CajeroEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cajero = it.getSerializable(ARG_CAJERO) as CajeroEntity
            esDelete = it.getBoolean(ARG_TIPO, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModificarEliminarCajerosBinding.inflate(inflater, container, false)

        if (cajero != null){

            binding.etId.text = Editable.Factory.getInstance().newEditable(cajero.id.toString())
            binding.etDireccion.text = Editable.Factory.getInstance().newEditable(cajero.direccion.toString())
            binding.etLatitud.text = Editable.Factory.getInstance().newEditable(cajero.latitud.toString())
            binding.etLongitud.text = Editable.Factory.getInstance().newEditable(cajero.longuitud.toString())

        }

        binding.btnSaveAtm.setOnClickListener {

            var id = binding.etId.text.toString().trim().toLong()

            if (esDelete == true){

                var cajeroEliminar = CajeroEntity(id = id, direccion = "", latitud = 0.0, longuitud = 0.0)

                if (cajero != null){
                    val direccion = binding.etDireccion.text.toString().trim()
                    val latitud = binding.etLatitud.text.toString().trim().toDouble()
                    val longuitud = binding.etLongitud.text.toString().trim().toDouble()

                    cajeroEliminar = CajeroEntity(id = id, direccion = direccion, latitud = latitud, longuitud = longuitud)

                }

                Thread{
                    CajeroApplication.database.cajeroDao().deleteCajero(cajeroEliminar)

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

        @JvmStatic
        fun newInstance(d: Boolean, cajero: CajeroEntity) =
            ModificarEliminarCajerosFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_TIPO, d)
                    putSerializable(ARG_CAJERO, cajero)
                }
            }
    }
}