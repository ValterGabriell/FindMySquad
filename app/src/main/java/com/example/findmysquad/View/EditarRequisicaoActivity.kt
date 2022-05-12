package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.EditarRequisicaoViewModel
import com.example.findmysquad.databinding.ActivityEditarRequisicaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class EditarRequisicaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarRequisicaoBinding
    private val model by inject<EditarRequisicaoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarRequisicaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHor.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.clock(this@EditarRequisicaoActivity)
            }
        }

        val idFieldUpdateDelete = intent?.extras?.getString("idField")
        binding.btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.updateRequisition(binding.chipGame, binding.chipPlata, idFieldUpdateDelete!!)
                finish()
            }
        }

        val idField = intent?.extras?.getString("idField")
        binding.btnDeleteRequisicao.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.deleteRequisicao(this@EditarRequisicaoActivity, idField!!)
            }
        }


    }
}