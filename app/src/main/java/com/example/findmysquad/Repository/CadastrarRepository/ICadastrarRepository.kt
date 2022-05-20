package com.example.findmysquad.Repository.CadastrarRepository

import android.content.Context
import android.widget.ProgressBar

interface ICadastrarRepository {
    fun cadastrarUsuario(email: String, senha: String, context: Context, progressBar: ProgressBar)

    fun changeActivity(context: Context)

}