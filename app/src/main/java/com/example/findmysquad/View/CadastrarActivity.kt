package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.findmysquad.ViewModel.CadastrarViewModel
import com.example.findmysquad.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: CadastrarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener {
            cadastrarUsuário()
        }


    }


    private fun cadastrarUsuário() {
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                model.cadastrarUsuario(email, senha, this@CadastrarActivity)
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                model.makeAToast(this@CadastrarActivity, "Preencha todos os campos", Toast.LENGTH_SHORT)
            }
        }
    }

    override fun onBackPressed() {

    }
}