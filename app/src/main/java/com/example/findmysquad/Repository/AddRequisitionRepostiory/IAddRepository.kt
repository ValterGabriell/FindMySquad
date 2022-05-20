package com.example.findmysquad.Repository.AddRequisitionRepostiory

import android.widget.ProgressBar
import com.google.android.material.chip.ChipGroup

interface IAddRepository {
    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup,progressBar: ProgressBar)
    fun uploadFotoProfile()
}