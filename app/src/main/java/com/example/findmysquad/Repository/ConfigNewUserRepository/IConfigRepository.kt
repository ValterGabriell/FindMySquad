package com.example.findmysquad.Repository.ConfigNewUserRepository

import android.content.Context
import android.net.Uri
import android.widget.EditText
import com.google.android.material.chip.ChipGroup

interface IConfigRepository {
    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    )

    fun abrirOTimerPickerEConfigurarAHora(context: Context)

    fun uparAImagemEscolhidaParaOBancoDeDados(filename: String, uri: Uri)

    fun baixaAFotoDoStorageAtualizaNoPerfilENoBancoDeDados()
}