package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.AddRequisitionRepostiory.AddRequisitonRepository
import com.google.android.material.chip.ChipGroup

class AddRequisitonViewModel(private val addRepository: AddRequisitonRepository) : ViewModel() {


    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup) {
       addRepository.addNewRequisicao(chipGroup, chipGroup2)
    }

    fun clock(context: Context) {
        addRepository.clock(context)
    }

    suspend fun pegarFotoEUparNaRequisicao(){

    }

}