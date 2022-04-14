package com.example.findmysquad.Repository.ConfigRepository

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.example.findmysquad.Model.FirebaseFeatures
import com.example.findmysquad.Model.Methods
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.ChipGroup

class ConfigRepository : IConfigRepository {

    private var timerDate: String = ""
    private var nickname: String = ""

    override suspend fun validateForm(
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
            FirebaseFeatures.getDatabase().collection("User").document(FirebaseFeatures.getAuth().currentUser?.uid.toString()).set(profileMap)
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

    override fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }

    private fun getNickname(et: EditText): String {
        nickname = et.text.toString()
        return nickname
    }

    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

}