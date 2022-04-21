package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import com.example.findmysquad.Model.ModelRequisicoes
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface ITelaPrincipalRepository {
    suspend fun signOut(context: Context)

    suspend fun receberDadosDeRequisicoes()

}