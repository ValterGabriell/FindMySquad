package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface ITelaPrincipalRepository {
    suspend fun signOut(context: Context)
    suspend fun configurarDados(id:String, listaRequisicoes : MutableLiveData<ArrayList<ModelRequisicoes>>)
}