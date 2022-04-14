package com.example.findmysquad.Repository.CadastrarRepository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.findmysquad.Model.FirebaseFeatures
import com.example.findmysquad.Model.Texts
import com.example.findmysquad.View.ConfigActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastrarRepository : ICadastrarRepository {

    override fun cadastrarUsuario(email: String, senha: String, context: Context) {
        FirebaseFeatures.getAuth().createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener {
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

    override fun changeActivity(context: Context) {
        val intent = Intent(context, ConfigActivity::class.java)
        context.startActivity(intent)
    }


}