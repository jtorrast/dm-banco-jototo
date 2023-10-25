package com.example.banco_jototo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_jototo.databinding.ActivityChangePassword2Binding
import com.google.android.material.snackbar.Snackbar

class ChangePasswordActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityChangePassword2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePassword2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirmPass.setOnClickListener {
            var newPass = binding.newPasswordEditFieldText.text.toString()
            var confirmNewPass = binding.confirmPasswordFieldEditTex.text.toString()


            if (newPass.equals(confirmNewPass)){

                Snackbar.make(binding.root,getString(R.string.snackbar_pass_change), Snackbar.LENGTH_SHORT).setAnchorView(binding.guideline).show()


            }else{
                binding.confirmPasswordFieldTex.error = getString(R.string.error_confirm_pass)
            }

        }

        binding.confirmPasswordFieldEditTex.onFocusChangeListener

        binding.btnExit.setOnClickListener {
            finish()
        }
    }
}