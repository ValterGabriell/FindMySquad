package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.MainViewModel
import com.example.findmysquad.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogar.setOnClickListener {
            cadastrarUsuário()
        }


    }


    private fun cadastrarUsuário() {
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if (email.isNotEmpty() || senha.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                model.cadastrarUsuario(email, senha, this@MainActivity)
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                model.makeAToast(this@MainActivity, "Preencha todos os campos", Toast.LENGTH_SHORT)
            }
        }
    }
}