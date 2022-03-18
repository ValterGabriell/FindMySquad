package com.example.findmysquad.ViewModel


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class MainViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    suspend fun cadastrarUsuario(email: String, senha: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            println("Debug: -> Cadastro criado com sucesso")
            changeActivity(context)
        }.addOnFailureListener { erro ->
            val msgErro = when (erro) {
                is FirebaseAuthWeakPasswordException -> "Digite uma senha de no minimo 6 caracteres"
                is FirebaseAuthUserCollisionException -> "Essa conta ja foi criada"
                is FirebaseAuthInvalidCredentialsException -> "Digite um email valido"
                is FirebaseNetworkException -> "Sem conexao"
                else -> "ERRO"
            }
            println("Debug: ->$msgErro")
        }
    }

    private fun changeActivity(context: Context) {
        val intent = Intent(context, TelaPrincipalActivity::class.java)
        context.startActivity(intent)
    }

    suspend fun makeAToast(context: Context, text: String, time: Int) {
        Toast.makeText(context, text, time).show()
    }


}