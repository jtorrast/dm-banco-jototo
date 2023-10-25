package com.example.banco_jototo

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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

        binding.idEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            val dni = binding.idEditText.text.toString().trim().uppercase()


            if (!hasFocus) {

                if (dni.isEmpty()) {
                    binding.idField.error = getString(R.string.error_empty_field)
                }else{
                    binding.idField.error = null

                    if (!validID(dni)){
                        binding.idField.error = getString(R.string.error_id_field)
                    }else{
                        binding.idField.error = null
                    }
                }
            }
        }

        binding.passwordEditField.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            val pass = binding.passwordEditField.text.toString().trim().uppercase()


            if (!hasFocus) {

                if (pass.isEmpty()) {
                    binding.passwordField.error = getString(R.string.error_empty_field)
                }else{
                    binding.passwordField.error = null

                    if (pass.length < 5){
                        binding.passwordField.error = getString(R.string.error_password_length)
                    }else{
                        binding.passwordField.error = null
                    }
                }
            }
        }




        binding.btnLogin.setOnClickListener {

            val dni = binding.idEditText.text.toString().trim().uppercase()
            val pass = binding.passwordEditField.text.toString().trim()
            var emptyfields = false
            var validFields = false

            if (dni.isEmpty()){
                binding.idField.error = getString(R.string.error_empty_field)
                emptyfields = true
            }

            if (pass.isEmpty()){
                binding.passwordField.error = getString(R.string.error_empty_field)
                emptyfields = true

            }

            if (validID(dni)){
                validFields = true
            }

            if (emptyfields && validFields == false){
                return@setOnClickListener
            }

            if (validID(dni)){
                if (pass.length >= 5){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("dni", dni)
                    startActivity(intent)
                }else{
                    binding.passwordField.error = getString(R.string.error_password_length)
                }
            }else{
                binding.idField.error = getString(R.string.error_id_field)
                return@setOnClickListener
            }

        }

        //boton salir aplicación
        binding.btnExit.setOnClickListener {
            finishAffinity()
        }
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