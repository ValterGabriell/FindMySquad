package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity

class TelaPrincipalRepository : ITelaPrincipalRepository {

    private val colletionProfile = FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)

    override suspend fun signOut(context: Context) {
        FirebaseFeatures.getAuth().signOut()
        context.startActivity(Intent(context, LogarActivity::class.java))
    }

    override suspend fun receberDadosDeRequisicoes() {
        val docRef = colletionProfile
        docRef.get().addOnSuccessListener { document ->
            for (result in document) {
                Log.d("Tag: ", "${result.id} => ${result.data}")
            }
        }
    }


}