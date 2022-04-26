package com.example.findmysquad.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.Repository.TelaPrincipalRepository.TelaPrincipalRepository

class TelaPrincipalViewModel(private val telaPrincipalRepository: TelaPrincipalRepository) :
    ViewModel() {

    val listaRequisicoes = MutableLiveData<ArrayList<ModelRequisicoes>>()

    suspend fun signOut(context: Context) {
        telaPrincipalRepository.signOut(context)
    }

    suspend fun configurarDados(id:String) {
      telaPrincipalRepository.configurarDados(id, listaRequisicoes)
    }

}
