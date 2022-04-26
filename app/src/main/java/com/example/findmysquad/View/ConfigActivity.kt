package com.example.findmysquad.View

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.ViewModel.ConfigViewModel
import com.example.findmysquad.databinding.ActivityConfigBinding
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private val model by inject<ConfigViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnHor.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.abrirOTimerPickerEConfigurarAHora(this@ConfigActivity)
            }
        }

        /**
         * Método para recuperar a imagem
         * */
        pegarImagemDaGaleriaEPassarParaOBD()


        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.validateForm(
                    binding.etNick,
                    binding.chipGroup,
                    binding.chipGroup2,
                    this@ConfigActivity
                )
            }
            CoroutineScope(Dispatchers.IO).launch {
                model.receberFotoEPorNoBancoDeDados()
            }

        }

    }

    private fun pegarImagemDaGaleriaEPassarParaOBD() {
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.img.setImageURI(it)
                enviarFotoParaOStorage(FirebaseFeatures.getAuth().currentUser?.uid.toString(), it!!)
            }
        )
        binding.img.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    private fun enviarFotoParaOStorage(filename: String, uri: Uri) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                model.enviarFotoParaOStorage(filename, uri)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ConfigActivity,
                        "Sucesso ao enviar a foto",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ConfigActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onBackPressed() {

    }
}
