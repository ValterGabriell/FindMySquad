package com.example.findmysquad.Repository.ConfigRepository

import android.content.Context
import android.widget.EditText
import com.example.findmysquad.Model.Methods
import com.google.android.material.chip.ChipGroup

interface IConfigRepository {
    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context
        : Context
    )

    fun clock(context: Context)
}