package com.example.findmysquad.ViewModel

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Repository.EditProfileRepository.EditProfileRepository

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

}