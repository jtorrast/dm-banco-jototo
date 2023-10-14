package com.example.banco_jototo

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.banco_jototo.databinding.ActivityLoginBinding
import com.example.banco_jototo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val dni = binding.idEditText.text.toString().trim()
            val pass = binding.passwordEditField.text.toString().trim()
            var emptyfields = false

            /*if (dni.isEmpty() && pass.isEmpty()){
                binding.idField.error = getString(R.string.error_empty_field)
                binding.idField.error = getString(R.string.error_empty_field)
                return@setOnClickListener
            }*/

            if (dni.isEmpty()){
                binding.idField.error = getString(R.string.error_empty_field)

                //binding.idField.error = getString(R.string.required)
                //binding.idField.error = null
                //Snackbar.make(it, "Debes completar ambos campos", Snackbar.LENGTH_LONG).show()

                /*binding.idField.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                binding.idField.setBoxStrokeColorStateList(ColorStateList.valueOf(Color.RED))
                binding.idField.hintTextColor = ColorStateList.valueOf(Color.RED)*/

                emptyfields = true

            }

            if (pass.isEmpty()){
                binding.passwordField.error = getString(R.string.error_empty_field)

                /*binding.passwordField.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                binding.passwordField.setBoxStrokeColorStateList(ColorStateList.valueOf(Color.RED))
                binding.passwordField.hintTextColor = ColorStateList.valueOf(Color.RED)*/
                emptyfields = true
            }

            if (emptyfields){
                return@setOnClickListener
            }


            if (validID(binding.idEditText.text.toString())){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                binding.idField.error = getString(R.string.error_id_field)

                /*binding.idField.helperText = "DNI Invalido"
                binding.idField.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                binding.idField.setBoxStrokeColorStateList(ColorStateList.valueOf(Color.RED))
                binding.idField.hintTextColor = ColorStateList.valueOf(Color.RED)*/

            }

        }


        //boton salir aplicación
        binding.btnExit.setOnClickListener {
            finish()
        }

        //setContentView(R.layout.activity_login)
    }

    fun validID(dni: String): Boolean{

        if (dni.length != 9){
            return false
        }

        val numberDNI = dni.substring(0, 8)
        val letter = dni.substring(8).uppercase()

        val number = numberDNI.toInt()
        val validLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
        val calculatedLetter = validLetters[number % 23]

        if (letter == calculatedLetter.toString().uppercase()){
            return true
        }

        return false


    }
}