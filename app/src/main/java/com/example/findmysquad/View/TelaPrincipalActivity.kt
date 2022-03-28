package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.findmysquad.R
import com.example.findmysquad.ViewModel.TelaPrincipalViewModel
import com.example.findmysquad.databinding.ActivityTelaPrincipalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTelaPrincipalBinding
    private val model : TelaPrincipalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnConfirmar.setOnClickListener {

            val r6 = binding.checkr6.text.toString()
            val cs = binding.checckCS.text.toString()
            val vava = binding.checkvava.text.toString()

            val listaGames = listOf(
                r6, cs, vava
            )

            CoroutineScope(Dispatchers.IO).launch {
                model.createAProfileData(listaGames)
                model.createGamesData()
            }
        }


    }
}