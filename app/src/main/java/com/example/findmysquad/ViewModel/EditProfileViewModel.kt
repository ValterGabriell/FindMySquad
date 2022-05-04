package com.example.findmysquad.ViewModel

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Repository.EditProfileRepository.EditProfileRepository
import com.google.android.material.chip.ChipGroup

class EditProfileViewModel(private val editProfileRepository: EditProfileRepository) : ViewModel() {


    suspend fun recuperarDadosUsuario(
        editTextName: EditText,
        editTextEmail: EditText,
        img: ImageView
    ) {
        editProfileRepository.recuperarDadosUsuario(editTextName, editTextEmail, img)
    }

    suspend fun deletarConta(context: Context) {
        editProfileRepository.deletarConta(context)
    }

    suspend fun atualizarCadastroUsuario(
        et: EditText,
        etEmail : EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    ) {
        editProfileRepository.atualizarPerfil(et, etEmail,chipGroup, chipGroup2, context)
    }

    suspend fun abrirOTimerPickerEConfigurarAHora(context: Context){
        editProfileRepository.abrirOTimerPickerEConfigurarAHora(context)
    }


}