package com.example.findmysquad.Repository.LogarRepository

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.View.TelaPrincipalActivity

class LogarRepository : ILogarRepository {
    override suspend fun logarUsuário(
        email: String,
        senha: String,
        context: Context,
        progressBar: ProgressBar
    ) {
        FirebaseFeatures.getAuth().signInWithEmailAndPassword(email, senha).addOnSuccessListener {
            changeActivity(context)
            progressBar.visibility = View.GONE
        }.addOnFailureListener { erro ->
            Toast.makeText(context, "Erro ao logar usuário: ${erro.message}", Toast.LENGTH_LONG)
                .show()
            progressBar.visibility = View.GONE
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