package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.EditRequistionRepository.EditRequisitonRepository
import com.google.android.material.chip.ChipGroup

class EditarRequisicaoViewModel(private val repository: EditRequisitonRepository) : ViewModel() {

    suspend fun clock(context: Context) {
        repository.clock(context)
    }

    suspend fun updateRequisition(chipGroup: ChipGroup, chipGroup2: ChipGroup, idField: String) {
        repository.updateRequisicao(chipGroup, chipGroup2, idField)
    }

    suspend fun deleteRequisicao(context: Context, idField: String) {
        repository.deleteRequisicao(context, idField)
    }


}