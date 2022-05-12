package com.example.findmysquad.Repository.MyReqRep

import androidx.lifecycle.MutableLiveData
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts

class MyReqRep : IMyReqRep {
    override suspend fun recuperarRequisicoesPorUsuario(
        id: String,
        lista: MutableLiveData<ArrayList<ModelRequisicoes>>
    ) {
        val query = FirebaseFeatures.getDatabase()
            .collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME)
            .whereEqualTo(
                "User", id
            ).get()
        val listaModel = ArrayList<ModelRequisicoes>()
        query.addOnSuccessListener { documents ->
            for (ids in 0 until documents.size()) {
                val model = ModelRequisicoes()
                model.apply {
                    user = documents.documents[ids].get("UserNick").toString()
                    horario = documents.documents[ids].get("Horário").toString()
                    game = documents.documents[ids].data?.get("Jogo") as ArrayList<Int>
                    plataforma = documents.documents[ids].data?.get("Plataforma") as ArrayList<Int>
                    photoUri = documents.documents[ids].get("PhotoUri").toString()
                    idField = documents.documents[ids].get("IdReq").toString()
                    listaModel.add(model)
                }
            }
            listaModel.reverse()
            lista.postValue(listaModel)
        }
    }
}