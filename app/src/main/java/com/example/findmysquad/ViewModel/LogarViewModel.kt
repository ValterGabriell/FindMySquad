package com.example.findmysquad.ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.Texts
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*

class LogarViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    suspend fun logarUsuário(email:String, senha:String, context: Context){
        auth.signInWithEmailAndPassword(email, senha).addOnSuccessListener {
            changeActivity(context)
        }.addOnFailureListener { erro ->
            val msgErro = when (erro) {
                is FirebaseAuthWeakPasswordException -> Texts.SENHA_INVALIDA
                is FirebaseAuthUserCollisionException -> Texts.CONTA_CRIADA
                is FirebaseAuthInvalidCredentialsException -> Texts.EMAIL_INVALIDO
                is FirebaseNetworkException -> Texts.SEM_CONEXAO
                else -> "ERRO"
            }
            Toast.makeText(context, msgErro, Toast.LENGTH_SHORT).show()
        }
    }


    private fun changeActivity(context: Context) {
        val intent = Intent(context, TelaPrincipalActivity::class.java)
        context.startActivity(intent)
    }

    suspend fun makeAToast(context: Context, text: String, time: Int) {
        Toast.makeText(context, text, time).show()
    }

    fun onStart(context: Context){
        if (auth.currentUser != null){
            context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
        }
    }



}