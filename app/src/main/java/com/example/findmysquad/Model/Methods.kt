package com.example.findmysquad.Model

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object Methods {
    @SuppressLint("SimpleDateFormat")
    fun configTimerPicker(context: Context): String {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
        }
        TimePickerDialog(
            context,
            timePickerDialog,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
        return SimpleDateFormat("HH:mm").format(calendar.time)
    }
}