package com.example.findmysquad.ViewModel

import android.content.Context
import android.net.Uri
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.ConfigNewUserRepository.ConfigNewUserRepository
import com.google.android.material.chip.ChipGroup

class ConfigViewModel(private val configRepository: ConfigNewUserRepository) : ViewModel() {

    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    ) {
        configRepository.validateForm(et, chipGroup, chipGroup2, context)
    }

    suspend fun abrirOTimerPickerEConfigurarAHora(context: Context){
        configRepository.abrirOTimerPickerEConfigurarAHora(context)
    }

    suspend fun enviarFotoParaOStorage(filename:String, uri:Uri){
        configRepository.uparAImagemEscolhidaParaOBancoDeDados(filename, uri)
    }

    suspend fun receberFotoEPorNoBancoDeDados(){
        configRepository.baixaAFotoDoStorageAtualizaNoPerfilENoBancoDeDados()
    }


}