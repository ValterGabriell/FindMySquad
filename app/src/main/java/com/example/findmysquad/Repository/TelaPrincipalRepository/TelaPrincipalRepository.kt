package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity

class TelaPrincipalRepository : ITelaPrincipalRepository {

    override suspend fun signOut(context: Context) {
        FirebaseFeatures.getAuth().signOut()
        context.startActivity(Intent(context, LogarActivity::class.java))
    }

    override suspend fun configurarDados(
        id: String,
        listaRequisicoes: MutableLiveData<ArrayList<ModelRequisicoes>>
    ) {
        val query =
            FirebaseFeatures.getDatabase()
                .collection(Texts.REQUISICAO_GERAL).whereNotEqualTo("User", id).get()
        val lista = ArrayList<ModelRequisicoes>()
        query.addOnSuccessListener {
            for (ids in 0 until it.size()) {
                val modelador = ModelRequisicoes()
                modelador.apply {
                    user = it.documents[ids].data?.get("UserNick").toString()
                    userId = it.documents[ids].data?.get("User").toString()
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