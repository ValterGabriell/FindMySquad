package com.example.findmysquad.ViewModel

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Repository.ConfigRepository.ConfigRepository
import com.google.android.material.chip.ChipGroup

class ConfigViewModel(private val configRepository: ConfigRepository) : ViewModel() {

    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    ) {
        configRepository.validateForm(et, chipGroup, chipGroup2, context)
    }

    suspend fun clock(context: Context){
        configRepository.clock(context)
    }
}