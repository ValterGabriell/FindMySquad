package com.example.findmysquad.Repository.EditRequistionRepository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditRequisitonRepository : IEditRequisitionRepository {
    private var timerDate: String = ""
    private var fieldValue: String = ""
    override fun updateRequisicao(chipGroup: ChipGroup, chipGroup2: ChipGroup, idField: String) {
        val data = hashMapOf(
            "Jogo" to filterChip(chipGroup),
            "Horário" to timerDate,
            "User" to FirebaseFeatures.getAuth().currentUser?.uid,
            "UserNick" to FirebaseFeatures.getAuth().currentUser?.displayName,
            "Plataforma" to filterChip(chipGroup2),
            "PhotoUri" to FirebaseFeatures.getAuth().currentUser?.photoUrl.toString(),
            "Email" to FirebaseFeatures.getAuth().currentUser?.email
        )
        salvar(data, idField)
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

    override fun deleteRequisicao(context: Context, idField: String) {

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(idField).delete()
            .addOnSuccessListener {
                Toast.makeText(context, "Excluido com sucesso", Toast.LENGTH_LONG).show()
                context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
            }
    }


    private fun filterChip(chipGroup: ChipGroup): MutableList<Int> {
        return chipGroup.checkedChipIds
    }

    private fun salvar(data: HashMap<String, Any?>, idField: String) {


        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(idField).delete()

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_GERAL).document(idField).delete()

        fieldValue = FieldValue.increment(1).toString()
        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(fieldValue).set(data)

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
            .collection(Texts.LISTA_REQUISICAO_NAME).document(fieldValue)
            .update("IdReq", fieldValue)


        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_GERAL).document(fieldValue)
            .set(data)

        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_GERAL).document(fieldValue)
            .update("IdReq", fieldValue)

    }

    fun clock(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }
}