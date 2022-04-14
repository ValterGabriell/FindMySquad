package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.AddRepostiory.AddRepository
import com.google.android.material.chip.ChipGroup

class AddViewModel(private val addRepository: AddRepository) : ViewModel() {


    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup) {
       addRepository.addNewRequisicao(chipGroup, chipGroup2)
    }

    fun clock(context: Context) {
        addRepository.clock(context)
    }

}