package com.example.findmysquad.View

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.CadastrarViewModel
import com.example.findmysquad.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CadastrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by inject<CadastrarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnCadastrar.setOnClickListener {
            cadastrarUsuário()
        }


    }

    private fun cadastrarUsuário() {
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.cadastrarUsuario(email, senha, this@CadastrarActivity)
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