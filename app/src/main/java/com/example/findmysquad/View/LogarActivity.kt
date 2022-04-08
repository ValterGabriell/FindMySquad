package com.example.findmysquad.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.LogarViewModel
import com.example.findmysquad.databinding.ActivityLogarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class LogarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogarBinding
    private val model: LogarViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogar.setOnClickListener {
            logar()
        }

        binding.txtNaoTemConta.setOnClickListener {
            startActivity(Intent(this, CadastrarActivity::class.java))
        }
    }

    private fun logar() {
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                model.logarUsuário(email, senha, this@LogarActivity)
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                model.makeAToast(this@LogarActivity, "Preencha todos os campos", Toast.LENGTH_SHORT)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        model.onStart(this)
    }

    override fun onBackPressed() {

    }



}