package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.Model.FirebaseFeatures
import com.example.findmysquad.Model.Methods
import com.example.findmysquad.Model.NamesDB
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.FirebaseAuth

class AddViewModel : ViewModel() {

    private var timerDate: String = ""
    private val auth = FirebaseFeatures.getAuth()
    private val db = FirebaseFeatures.getDatabase()

    /**
     * Metodo para adicionar uma nova requisicao
     */
    fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup) {
        val data = hashMapOf(
            "Jogo" to filterChip(chipGroup),
            "Horário" to timerDate,
            "User" to auth.currentUser?.uid,
            "Plataforma" to filterChip(chipGroup2)
        )
        salvar(data)


    }

    private fun salvar(data: HashMap<String, Any?>) {
        db.collection(NamesDB.REQUISICAO_NAME).document(auth.currentUser?.uid.toString())
            .collection("Requisições").add(data)
    }


    fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }

    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }




}