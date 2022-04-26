package com.example.findmysquad.Repository.MyReqRep

import androidx.lifecycle.MutableLiveData
import com.example.findmysquad.Model.ModelRequisicoes

interface IMyReqRep {

    suspend fun recuperarRequisicoesPorUsuario(
        id: String,
        lista: MutableLiveData<ArrayList<ModelRequisicoes>>
    )


}