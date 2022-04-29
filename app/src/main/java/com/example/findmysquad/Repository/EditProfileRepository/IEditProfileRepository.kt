package com.example.findmysquad.Repository.EditProfileRepository

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

interface IEditProfileRepository {
    suspend fun recuperarDadosUsuario(
        editTextNickname: EditText,
        editTextEmail: EditText,
        img: ImageView
    )

    suspend fun deletarConta(context: Context)
}