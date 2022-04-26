package com.example.findmysquad.Repository.ConfigRepository

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.ktx.userProfileChangeRequest

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