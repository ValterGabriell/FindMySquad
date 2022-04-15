package com.example.findmysquad.Repository.LogarRepository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.findmysquad.Model.FirebaseFeatures
import com.example.findmysquad.View.TelaPrincipalActivity

class LogarRepository : ILogarRepository {
    override suspend fun logarUsuário(email: String, senha: String, context: Context) {
        FirebaseFeatures.getAuth().signInWithEmailAndPassword(email, senha).addOnSuccessListener {
            changeActivity(context)
        }.addOnFailureListener { erro ->
            Toast.makeText(context, "Erro ao logar usuário: ${erro.cause}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override suspend fun makeAToast(context: Context, text: String, time: Int) {
        Toast.makeText(context, text, time).show()
    }

    private fun changeActivity(context: Context) {
        val intent = Intent(context, TelaPrincipalActivity::class.java)
        context.startActivity(intent)
    }

}