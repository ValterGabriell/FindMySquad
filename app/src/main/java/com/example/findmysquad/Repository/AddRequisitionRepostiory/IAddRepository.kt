package com.example.findmysquad.Repository.AddRequisitionRepostiory

import com.google.android.material.chip.ChipGroup

interface IAddRepository {
    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup)
    fun uploadFotoProfile()
}