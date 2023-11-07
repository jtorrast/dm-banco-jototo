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
import android.widget.Toast
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityLoginBinding
import com.example.banco_jototo.databinding.ActivityMainBinding
import com.example.banco_jototo.pojo.Cliente
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var passwordLength = 4

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

                    if (pass.length < passwordLength){
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
                if (pass.length >= passwordLength){
                    //añadir API de login

                    val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

                    var clienteLogin = Cliente()

                    clienteLogin.setNif(dni)
                    clienteLogin.setClaveSeguridad(pass)

                    //Comprobamos si e cliente existe en la BD

                    val existClient = mbo?.login(clienteLogin) ?: -1

                    if (existClient == -1){
                        binding.passwordField.error = getString(R.string.error_wrong_password)
                    }else{
                        /*Descomentar cuando funcione la animación*/
                        //val intent = Intent(this, LottieActivity::class.java)
                        /*Comentar cuando funcione la animación*/
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("Cliente", existClient)
                        startActivity(intent)
                    }
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

        //Comprobación para DNI inventados con 8 digitos y una letra
        val patron = Regex("^[0-9]{8}[A-Za-z]\$")

        return patron.matches(dni)

        /*
        * COMPROBACIÓN DNI CON LETRA
        *
        * como en la API los DNI no son reales no podemos usar esta comprobación
        *
        *
        * */
        /*if (dni.length != 9){
            return false
        }

        val numberDNI = dni.substring(0, 8)
        val letter = dni.substring(8).uppercase()

        val number = numberDNI.toInt()
        val validLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
        val calculatedLetter = validLetters[number % 23]

        if (letter == calculatedLetter.toString().uppercase()){
            return true
        }*/

        //return false

    }
}