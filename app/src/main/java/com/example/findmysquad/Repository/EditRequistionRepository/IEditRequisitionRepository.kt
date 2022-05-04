package com.example.findmysquad.Repository.EditRequistionRepository

import com.google.android.material.chip.ChipGroup

interface IEditRequisitionRepository {
    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup)
    fun uploadFotoProfile()
}