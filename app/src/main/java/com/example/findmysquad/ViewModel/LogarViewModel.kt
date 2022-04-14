package com.example.findmysquad.ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.FirebaseFeatures
import com.example.findmysquad.Model.Texts
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*

class LogarViewModel : ViewModel() {

    suspend fun logarUsuário(email: String, senha: String, context: Context) {
        FirebaseFeatures.getAuth().signInWithEmailAndPassword(email, senha).addOnSuccessListener {
            changeActivity(context)
        }.addOnFailureListener { erro ->
            Toast.makeText(context, "Erro ao logar usuário: ${erro.cause}", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun changeActivity(context: Context) {
        val intent = Intent(context, TelaPrincipalActivity::class.java)
        context.startActivity(intent)
    }

    suspend fun makeAToast(context: Context, text: String, time: Int) {
        Toast.makeText(context, text, time).show()
    }

    fun onStart(context: Context) {
        if (FirebaseFeatures.getAuth().currentUser != null) {
            context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
        }
    }


}