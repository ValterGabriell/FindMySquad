package com.example.findmysquad.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.EditProfileViewModel
import com.example.findmysquad.databinding.ActivityEditProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val model by inject<EditProfileViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.IO).launch {
            model.recuperarDadosUsuario(binding.etNick, binding.etEmailProfile, binding.img)
        }

        binding.btnHor.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.abrirOTimerPickerEConfigurarAHora(this@EditProfileActivity)
            }
        }

        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.atualizarCadastroUsuario(
                    binding.etNick,
                    binding.etEmailProfile,
                    binding.chipGroup,
                    binding.chipGroup2,
                    this@EditProfileActivity
                )
            }
        }


        binding.btnDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.deletarConta(this@EditProfileActivity)
            }

        }


    }
}