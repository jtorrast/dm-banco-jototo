package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.banco_jototo.bd.MiBancoOperacional
import com.example.banco_jototo.databinding.ActivityChangePassword2Binding
import com.example.banco_jototo.pojo.Cliente
import com.google.android.material.snackbar.Snackbar

class ChangePasswordActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityChangePassword2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePassword2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding.newPasswordEditFieldText.onFocusChangeListener =View.OnFocusChangeListener { v, hasFocus ->
            var password = binding.newPasswordEditFieldText.text.toString()
            if (!hasFocus){
                if (password.isEmpty()){
                    binding.newPasswordFieldTex.error = getString(R.string.error_empty_edit_text)
                }else{
                    binding.newPasswordFieldTex.error = null

                    if (password.length < 4){
                        binding.newPasswordFieldTex.error = getString(R.string.error_password_length)
                    }else{
                        binding.newPasswordFieldTex.error = null
                    }

                }

            }
        }

        binding.confirmPasswordFieldEditTex.onFocusChangeListener =View.OnFocusChangeListener { v, hasFocus ->
            var password = binding.confirmPasswordFieldEditTex.text.toString()
            if (!hasFocus){
                if (password.isEmpty()){
                    binding.confirmPasswordFieldTex.error = getString(R.string.error_empty_edit_text)
                }else{
                    binding.confirmPasswordFieldTex.error = null

                    if (password.length < 4){
                        binding.confirmPasswordFieldTex.error = getString(R.string.error_password_length)
                    }else{
                        binding.confirmPasswordFieldTex.error = null
                    }
                }


            }



        }

        binding.btnConfirmPass.setOnClickListener {
            val cliente = intent.getSerializableExtra("Cliente") as Cliente

            var newPass = binding.newPasswordEditFieldText.text.toString()
            var confirmNewPass = binding.confirmPasswordFieldEditTex.text.toString()

            if (newPass.isEmpty()){
                binding.newPasswordFieldTex.error = getString(R.string.error_empty_edit_text)
                return@setOnClickListener
            }

            if (confirmNewPass.isEmpty()){
                binding.confirmPasswordFieldTex.error = getString(R.string.error_empty_edit_text)
                return@setOnClickListener
            }

            if (newPass.length < 4){
                binding.newPasswordFieldTex.error = getString(R.string.error_password_length)
                return@setOnClickListener
            }else{
                binding.newPasswordFieldTex.error = null
            }

            if (confirmNewPass.length < 4){
                binding.confirmPasswordFieldTex.error = getString(R.string.error_password_length)
                return@setOnClickListener
            }else{
                binding.confirmPasswordFieldTex.error = null
            }


            if (newPass.equals(confirmNewPass)){

                cliente.setClaveSeguridad(confirmNewPass)
                val passwordChanged = mbo?.changePassword(cliente)
                binding.newPasswordEditFieldText.text = null
                binding.confirmPasswordFieldEditTex.text = null

                if (passwordChanged == 1) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.snackbar_pass_change),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(binding.guideline).show()
                }else{
                    Snackbar.make(
                        binding.root, getString(R.string.snackbar_pass_unchanged),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(binding.guideline).show()
                }


            }else{
                binding.confirmPasswordFieldTex.error = getString(R.string.error_confirm_pass)
            }

        }


        binding.btnExit.setOnClickListener {
            finish()
        }
    }
}