package com.example.findmysquad.Repository.ConfigRepository

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.chip.ChipGroup

interface IConfigRepository {
    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context,
        img:ImageView
    )

    fun clock(context: Context)
}