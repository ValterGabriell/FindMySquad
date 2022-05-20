package com.example.findmysquad.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.CadastrarViewModel
import com.example.findmysquad.databinding.ActivityCadastrarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CadastrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarBinding
    private val viewModel by inject<CadastrarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnCadastrar.setOnClickListener {
            binding.progressBar3.visibility = View.VISIBLE
            cadastrarUsuário()
        }


    }

    private fun cadastrarUsuário() {
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.cadastrarUsuario(email, senha, this@CadastrarActivity, binding.progressBar3)
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.makeAToast(
                    this@CadastrarActivity,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }
}