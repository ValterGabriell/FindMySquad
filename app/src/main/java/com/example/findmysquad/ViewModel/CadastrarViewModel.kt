package com.example.findmysquad.ViewModel


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.CadastrarRepository.CadastrarRepository

class CadastrarViewModel(private val cadastrarRepository: CadastrarRepository) : ViewModel() {

    suspend fun cadastrarUsuario(email: String, senha: String, context: Context){
        cadastrarRepository.cadastrarUsuario(email, senha, context)
    }

    suspend fun makeAToast(context: Context, text: String, time: Int) {
        Toast.makeText(context, text, time).show()
    }


}