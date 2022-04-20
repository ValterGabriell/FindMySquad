package com.example.findmysquad.ViewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Repository.LogarRepository.LogarRepository
import com.example.findmysquad.View.TelaPrincipalActivity

class LogarViewModel(private val logarRepository: LogarRepository) : ViewModel() {

    suspend fun logarUsuário(email: String, senha: String, context: Context) {
        logarRepository.logarUsuário(email, senha, context)
    }

    suspend fun makeAToast(context: Context, text: String, time: Int) {
      logarRepository.makeAToast(context, text, time)
    }

    fun onStart(context: Context) {
        if (FirebaseFeatures.getAuth().currentUser != null) {
            context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
        }
    }


}