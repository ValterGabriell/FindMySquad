package com.example.findmysquad.Repository.EditProfileRepository

import android.content.Context
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.findmysquad.Model.Objects.Methods
import com.google.android.material.chip.ChipGroup

interface IEditProfileRepository {
    suspend fun recuperarDadosUsuario(
        editTextNickname: EditText,
        editTextEmail: EditText,
        img: ImageView
    )

    fun baixaAFotoDoStorageAtualizaNoPerfilENoBancoDeDados()

    suspend fun deletarConta(context: Context)

    suspend fun atualizarPerfil(
        et: EditText,
        etEmail : EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    )

    suspend fun uparAImagemEscolhidaParaOBancoDeDados(filename: String, uri: Uri)

    fun abrirOTimerPickerEConfigurarAHora(context: Context)
}