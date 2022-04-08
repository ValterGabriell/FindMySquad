package com.example.findmysquad.ViewModel

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ConfigViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private var timerDate: String = ""
    private var nickname: String = ""

    @SuppressLint("SimpleDateFormat")
    suspend fun configTimerPicker(context: Context): String {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            timerDate = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            context,
            timePickerDialog,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
        return timerDate
    }

    private fun getNickname(et: EditText): String {
        nickname = et.text.toString()
        return nickname
    }


    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

    suspend fun validateForm(
        et: EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    ) {
        if (getNickname(et).isNotEmpty()) {
            val profileMap = hashMapOf(
                "nickname" to getNickname(et),
                "horario" to timerDate,
                "lista-jogos" to filterChip(chipGroup),
                "lista-plataformas" to filterChip(chipGroup2)
            )
            db.collection("User").document(auth.currentUser?.uid.toString()).set(profileMap)
                .addOnSuccessListener {
                    context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Erro ao continuar cadastro: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}