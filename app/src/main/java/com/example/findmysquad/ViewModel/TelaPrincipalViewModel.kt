package com.example.findmysquad.ViewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Repository.TelaPrincipalRepository.TelaPrincipalRepository


class TelaPrincipalViewModel(private val telaPrincipalRepository: TelaPrincipalRepository) :
    ViewModel() {

    val listaRequisicoes = MutableLiveData<ArrayList<ModelRequisicoes>>()

    suspend fun signOut(context: Context) {
        telaPrincipalRepository.signOut(context)
    }

    suspend fun configurarDados(id: String) {
        telaPrincipalRepository.configurarDados(id, listaRequisicoes)
    }

    fun recuperarNumeroCelular(context: Context, id: String) {
        FirebaseFeatures.getDatabase().collection("User")
            .document(id).get().addOnSuccessListener { task ->
                    val number = task.data?.getValue("numeroCelular")
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data =
                        Uri.parse("https://api.whatsapp.com/send?phone=$number&text=Olá, vim do FindMySquad e queria saber horas vai ser o game?")
                    context.startActivity(intent)
            }


    }

}
