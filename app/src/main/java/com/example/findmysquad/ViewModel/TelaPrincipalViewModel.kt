package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Repository.TelaPrincipalRepository.TelaPrincipalRepository

class TelaPrincipalViewModel(private val telaPrincipalRepository: TelaPrincipalRepository) :
    ViewModel() {

    suspend fun signOut(context: Context) {
        telaPrincipalRepository.signOut(context)
    }

    suspend fun recuperarDadosDeRequisicoes(){
        telaPrincipalRepository.receberDadosDeRequisicoes()
    }


}
