package com.example.findmysquad.Repository.EditRequistionRepository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.ktx.userProfileChangeRequest

class EditRequisitonRepository : IEditRequisitionRepository {
    private var timerDate: String = ""
    private val auth = FirebaseFeatures.getAuth().currentUser

    override fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup) {
        val data = hashMapOf(
            "Jogo" to filterChip(chipGroup),
            "Horário" to timerDate,
            "User" to FirebaseFeatures.getAuth().currentUser?.uid,
            "UserNick" to FirebaseFeatures.getAuth().currentUser?.displayName,
            "Plataforma" to filterChip(chipGroup2),
            "PhotoUri" to FirebaseFeatures.getAuth().currentUser?.photoUrl.toString(),
            "Email" to FirebaseFeatures.getAuth().currentUser?.email
        )
        salvar(data)
    }

    override fun uploadFotoProfile() {
        val profileUpdate = userProfileChangeRequest {
            FirebaseFeatures.getStorage()
                .child("images/${FirebaseFeatures.getAuth().currentUser?.uid}")
                .downloadUrl.addOnSuccessListener {
                    Log.d("Tag: ", it.toString())
                    FirebaseFeatures.getDatabase().collection("User")
                        .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
                        .update("photo", it.toString())
                }
        }
        FirebaseFeatures.getAuth().currentUser?.updateProfile(profileUpdate)
    }

    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

    private fun salvar(data: HashMap<String, Any?>) {

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).add(data)

    }

    fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }
}