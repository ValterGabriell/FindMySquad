package com.example.findmysquad.Repository.EditRequistionRepository

import android.content.Context
import com.google.android.material.chip.ChipGroup

interface IEditRequisitionRepository {
    fun updateRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup, idField: String)
    fun uploadFotoProfile()
    fun deleteRequisicao(context: Context, idField: String)

}