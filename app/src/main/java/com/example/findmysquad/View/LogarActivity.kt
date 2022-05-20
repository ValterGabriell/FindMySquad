package com.example.findmysquad.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.LogarViewModel
import com.example.findmysquad.databinding.ActivityLogarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LogarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogarBinding
    private val model by inject<LogarViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogar.setOnClickListener {
            binding.progressBar4.visibility = View.VISIBLE
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
                model.logarUsuário(email, senha, this@LogarActivity, binding.progressBar4)
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