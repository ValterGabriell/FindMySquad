package com.example.findmysquad.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.AddRequisitonViewModel
import com.example.findmysquad.databinding.ActivityAddNewRequisicaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AddNewRequisicao : AppCompatActivity() {


    private lateinit var binding: ActivityAddNewRequisicaoBinding
    private val model by inject<AddRequisitonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewRequisicaoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        CoroutineScope(Dispatchers.Main).launch {
            model.clock(this@AddNewRequisicao, binding.btnHor)
            binding.btnEnviar.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    model.addNewRequisicao(binding.chipGame, binding.chipPlata)
                    finish()
                }
            }


        }

    }
}