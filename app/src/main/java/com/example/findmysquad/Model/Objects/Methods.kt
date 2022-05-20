package com.example.findmysquad.Model.Objects

import android.app.TimePickerDialog
import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

object Methods {

    fun filterChip(chipGroup: ChipGroup): List<String> {
        return chipGroup.children
            .filter { (it as Chip).isChecked }
            .map { (it as Chip).text.toString() }.toList()
    }

    fun clock(
        context: Context,
        button: Button,
        timePickerDialog: TimePickerDialog.OnTimeSetListener
    ) {
        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            TimePickerDialog(context, timePickerDialog, hora, minutes, true).show()
            Toast.makeText(context, "Horário definido para $hora:$minutes", Toast.LENGTH_LONG)
                .show()
        }
    }
}