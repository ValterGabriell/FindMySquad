package com.example.findmysquad.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Repository.MyReqRep.MyReqRep

class MyReqRepViewModel(private val myReqRep: MyReqRep) : ViewModel() {

    val listaObserve = MutableLiveData<ArrayList<ModelRequisicoes>>()

    suspend fun receberDadosDoUnicoUsuario(id: String) {
        myReqRep.recuperarRequisicoesPorUsuario(id, listaObserve)
    }


}