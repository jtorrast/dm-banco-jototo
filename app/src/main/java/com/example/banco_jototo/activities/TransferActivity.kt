package com.example.banco_jototo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityTransferBinding
import com.google.android.material.snackbar.Snackbar

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //datos spinner cuenta

        //emisor
        val datos = arrayOf("ES71-4857-6875-1234", "ES71-4857-6452-6423", "ES71-5248-6374-4579", "ES71-4596-8547-1254")
        val adapterOwnAccount = ArrayAdapter(this, R.layout.spinner_item, datos)

        val spinnerOwnAccount = binding.spinnerAccount
        adapterOwnAccount.setDropDownViewResource(R.layout.spiner_drop_item)
        spinnerOwnAccount.adapter = adapterOwnAccount

        //receptor
        val adapterReciberOwnAccount = ArrayAdapter(this, R.layout.spinner_item, datos)

        val spinnerReciberOwnAccount = binding.spinnerReceiverOwnAccount
        adapterReciberOwnAccount.setDropDownViewResource(R.layout.spiner_drop_item)
        spinnerReciberOwnAccount.adapter = adapterReciberOwnAccount


        //datos spinner simbolo divisa
        val divisas = arrayOf("€", "$", "GBP")
        val adapterCurrency = ArrayAdapter(this, R.layout.spinner_item, divisas)

        val spinnerCurrency = binding.spCurrency

        adapterCurrency.setDropDownViewResource(R.layout.spiner_drop_item)

        spinnerCurrency.adapter = adapterCurrency

        //radio buttons
        binding.radioOwnAccount.setOnClickListener {
            binding.editElseAccount.visibility = View.INVISIBLE
            binding.spinnerReceiverOwnAccount.visibility = View.VISIBLE
        }

        binding.radioElseAccount.setOnClickListener {
            binding.spinnerReceiverOwnAccount.visibility = View.INVISIBLE
            binding.editElseAccount.visibility = View.VISIBLE
        }

        //boton enviar transferencia
        binding.btnSendTransfer.setOnClickListener {

            var fieldsValidation = true

            if (binding.checkboxGroup.checkedRadioButtonId == -1){
                Snackbar.make(binding.root, getString(R.string.snack_text_radioButton), Snackbar.LENGTH_SHORT).setAnchorView(binding.guideline2).show()
                fieldsValidation = false
            }
            if (binding.editElseAccount.visibility == View.VISIBLE && binding.editElseAccount.text.isNullOrEmpty()){
                    binding.editElseAccount.error = getString(R.string.error_empty_field)
                    val errorColor = getColor(R.color.red_error)
                    binding.editElseAccount.setHintTextColor(errorColor)
                    fieldsValidation = false
            }

            if (binding.editQnty.text.isNullOrEmpty() || binding.editQnty.text.toString().equals("0")){
                binding.editQnty.error = getString(R.string.error_empty_field)
                fieldsValidation = false
            }

            if (fieldsValidation){

                var textSender = getString(R.string.sender_account)+"\n"+binding.spinnerAccount.selectedItem.toString()
                var textRecipient:String? = null
                if (binding.radioOwnAccount.isChecked){
                    textRecipient = getString(R.string.recipient_own_account)+"\n"+binding.spinnerReceiverOwnAccount.selectedItem.toString()
                }else{
                    textRecipient = getString(R.string.recipient_account)+"\n"+binding.editElseAccount.text.toString()
                }
                var textAmount = getString(R.string.amount)+" "+binding.editQnty.text.toString()+binding.spCurrency.selectedItem.toString()
                var textRecipt:String? = null
                if (binding.checkboxReceipt.isChecked){
                    textRecipt = getString(R.string.text_checked_receipt)
                }else{
                    textRecipt = getString(R.string.text_not_checked_receipt)
                }

                var message = "$textSender\n$textRecipient\n$textAmount\n$textRecipt"

                var snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                snackbar.setTextMaxLines(6)
                snackbar.show()

            }
        }



        //boton cancelar
        binding.btnCancelTransfer.setOnClickListener {
            binding.checkboxGroup.clearCheck()
            binding.spinnerReceiverOwnAccount.visibility = View.INVISIBLE
            binding.editElseAccount.visibility = View.INVISIBLE
            binding.editElseAccount.text.clear()
            binding.editQnty.text.clear()
            binding.checkboxReceipt.isChecked = false
            binding.spinnerAccount.setSelection(0)
            binding.spinnerReceiverOwnAccount.setSelection(0)
            binding.spCurrency.setSelection(0)

        }



    }
}