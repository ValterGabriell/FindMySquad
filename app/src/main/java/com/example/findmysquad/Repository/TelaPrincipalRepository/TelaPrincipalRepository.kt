package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import java.util.*
import kotlin.collections.ArrayList

class TelaPrincipalRepository : ITelaPrincipalRepository {

    override suspend fun signOut(context: Context) {
        FirebaseFeatures.getAuth().signOut()
        context.startActivity(Intent(context, LogarActivity::class.java))
    }

    override suspend fun configurarDados(id:String, listaRequisicoes : MutableLiveData<ArrayList<ModelRequisicoes>>) {
        val query = FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME).whereNotEqualTo(
            "User", id
        ).get()
        val lista = ArrayList<ModelRequisicoes>()
        query.addOnSuccessListener {
            for (ids in 0 until it.size()) {
                val modelador = ModelRequisicoes()
                modelador.apply {
                    user = it.documents[ids].data?.get("UserNick").toString()
                    horario = it.documents[ids].data?.get("Horário").toString()
                    game = it.documents[ids].data?.get("Jogo") as ArrayList<Int>
                    plataforma = it.documents[ids].data?.get("Plataforma") as ArrayList<Int>
                    photoUri = it.documents[ids].data?.get("PhotoUri").toString()
                    lista.add(this)
                }
                lista.reverse()
                listaRequisicoes.postValue(lista)
            }
        }
    }

}