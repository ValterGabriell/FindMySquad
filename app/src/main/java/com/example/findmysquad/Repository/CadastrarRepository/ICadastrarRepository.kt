package com.example.findmysquad.Repository.CadastrarRepository

import android.content.Context

interface ICadastrarRepository {
    fun cadastrarUsuario(email: String, senha: String, context: Context)

    fun changeActivity(context: Context)

}