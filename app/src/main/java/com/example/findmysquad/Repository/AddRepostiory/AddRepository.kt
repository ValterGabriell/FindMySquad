package com.example.findmysquad.Repository.AddRepostiory

import android.content.Context
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.Model.Objects.Texts
import com.google.android.material.chip.ChipGroup

class AddRepository {
    private var timerDate: String = ""
    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup) {
        val data = hashMapOf(
            "Jogo" to filterChip(chipGroup),
            "Horário" to timerDate,
            "User" to FirebaseFeatures.getAuth().currentUser?.uid,
            "Plataforma" to filterChip(chipGroup2)
        )
        salvar(data)
    }

    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

    private fun salvar(data: HashMap<String, Any?>) {
        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME).add(data)
    }

    fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }
}