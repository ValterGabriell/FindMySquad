package com.example.findmysquad.ViewModel

import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.AddRequisitionRepostiory.AddRequisitonRepository
import com.google.android.material.chip.ChipGroup

class AddRequisitonViewModel(private val addRepository: AddRequisitonRepository) : ViewModel() {


    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup, progressBar: ProgressBar) {
       addRepository.addNewRequisicao(chipGroup, chipGroup2, progressBar)
    }

    fun clock(context: Context, button: Button) {
        addRepository.clock(context, button)
    }

}