package com.example.findmysquad.Repository.AddRequisitionRepostiory

import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TimePicker
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.Model.Objects.Texts
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import java.util.*

class AddRequisitonRepository : IAddRepository, TimePickerDialog.OnTimeSetListener {
    private var timerDate: String = ""
    private var fieldValue: String = ""
    private var savedHour = 0
    private var savedMinute = 0
    override fun addNewRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup, progressBar: ProgressBar) {
        val data = hashMapOf(
            "Jogo" to Methods.filterChip(chipGroup),
            "Horário" to timerDate,
            "User" to FirebaseFeatures.getAuth().currentUser?.uid,
            "UserNick" to FirebaseFeatures.getAuth().currentUser?.displayName,
            "Plataforma" to Methods.filterChip(chipGroup2),
            "PhotoUri" to FirebaseFeatures.getAuth().currentUser?.photoUrl.toString(),
            "Email" to FirebaseFeatures.getAuth().currentUser?.email,
            "IdReq" to fieldValue
        )
        salvar(data, progressBar)
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


    private fun salvar(data: HashMap<String, Any?>,progressBar: ProgressBar) {
        fieldValue = FieldValue.increment(1).toString()
        progressBar.visibility = View.VISIBLE
        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(fieldValue).set(data).addOnSuccessListener {
                progressBar.visibility = View.GONE
            }

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(fieldValue)
            .update("IdReq", fieldValue)



        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_GERAL).document(fieldValue)
            .set(data)

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_GERAL).document(fieldValue)
            .update("IdReq", fieldValue)


    }

    fun clock(context: Context, button: Button) {
        Methods.clock(context, button,this)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        timerDate = "$savedHour:$savedMinute"
    }
}