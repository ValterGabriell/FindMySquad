package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.AddViewModel
import com.example.findmysquad.databinding.ActivityAddNewRequisicaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AddNewRequisicao : AppCompatActivity() {

    private lateinit var binding : ActivityAddNewRequisicaoBinding
    private val model by inject<AddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewRequisicaoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnHor.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.clock(this@AddNewRequisicao)
            }
        }

        binding.btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.addNewRequisicao(binding.chipGame, binding.chipPlata)
                finish()
            }
        }



    }







}