package com.example.findmysquad.View

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.ViewModel.ConfigurarUserViewModel
import com.example.findmysquad.databinding.ActivityConfigBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private val model by inject<ConfigurarUserViewModel>()
    private var profileFoto: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)




        CoroutineScope(Dispatchers.Main).launch {
            model.abrirOTimerPickerEConfigurarAHora(this@AddUserActivity, binding.btnHor)
        }


        /**
         * Método para recuperar a imagem
         * */
        pegarImagemDaGaleriaEPassarParaOBD()


        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.validateForm(
                    binding.etNick,
                    binding.etNumber,
                    binding.chipGroup,
                    binding.chipGroup2,
                    this@AddUserActivity,
                    profileFoto
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
                Picasso.get().load(it).into(binding.img)
                enviarFotoParaOStorage(FirebaseFeatures.getAuth().currentUser?.uid.toString(), it!!)
                profileFoto = it.toString()
            }
        )
        binding.img.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    private fun enviarFotoParaOStorage(filename: String, uri: Uri) {
        if (uri.toString().isNotEmpty() and filename.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    model.enviarFotoParaOStorage(filename, uri)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddUserActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }

    }


    override fun onBackPressed() {

    }
}
