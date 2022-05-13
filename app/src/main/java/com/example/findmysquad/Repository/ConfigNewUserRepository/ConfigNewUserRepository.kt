package com.example.findmysquad.Repository.ConfigNewUserRepository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.children
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.ktx.userProfileChangeRequest

class ConfigNewUserRepository : IConfigRepository {

    private var timerDate: String = ""
    private var nickname: String = ""
    private var numero: String = ""
    private val imgRef = FirebaseFeatures.getStorage()
    private val auth = FirebaseFeatures.getAuth().currentUser

    override suspend fun validateForm(
        et: EditText,
        etNumero:EditText,
        chipGroup: ChipGroup,
        chipGroup2: ChipGroup,
        context: Context
    ) {
        if (pegarOTextoDoEditTextNicknameEPorComoNickDoUsuario(et).isNotEmpty()) {
            val profileMap = hashMapOf(
                "nickname" to pegarOTextoDoEditTextNicknameEPorComoNickDoUsuario(et),
                "horario" to timerDate,
                "lista-jogos" to filtrarOQueFoiClicadoNoChipGroup(chipGroup),
                "lista-plataformas" to filtrarOQueFoiClicadoNoChipGroup(chipGroup2),
                "userId" to auth?.uid.toString(),
                "email" to auth?.email.toString(),
                "numeroCelular" to pegarOTextoDoEditTextNicknameEPorComoNumeroDoUsuario(etNumero)
            )
            /**
             * upar perfil do usuario
             */
            criarAColeçãoNoFirebase(et, context, profileMap)

        }
    }

    private fun criarAColeçãoNoFirebase(et: EditText, context: Context, profileMap: Any) {
        FirebaseFeatures.getDatabase().collection("User")
            .document(FirebaseFeatures.getAuth().currentUser?.uid.toString()).set(profileMap)
            .addOnSuccessListener {
                context.startActivity(Intent(context, TelaPrincipalActivity::class.java))
            }.addOnFailureListener {
                Toast.makeText(
                    context,
                    "Erro ao continuar cadastro: ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        val profileUpdate = userProfileChangeRequest {
            displayName = pegarOTextoDoEditTextNicknameEPorComoNickDoUsuario(et)
        }



        FirebaseFeatures.getAuth().currentUser?.updateProfile(profileUpdate)
    }

    override fun abrirOTimerPickerEConfigurarAHora(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }


    override fun uparAImagemEscolhidaParaOBancoDeDados(filename: String, uri: Uri) {
        imgRef.child("images/$filename").putFile(uri)
    }

    override fun baixaAFotoDoStorageAtualizaNoPerfilENoBancoDeDados() {
        FirebaseFeatures.getStorage()
            .child("images/${FirebaseFeatures.getAuth().currentUser?.uid}")
            .downloadUrl.addOnSuccessListener { uri ->
                FirebaseFeatures.getDatabase().collection("User")
                    .document(FirebaseFeatures.getAuth().currentUser?.uid.toString())
                    .update("photo", uri.toString())

                val profileUpdate = userProfileChangeRequest {
                    photoUri = uri
                }
                FirebaseFeatures.getAuth().currentUser?.updateProfile(profileUpdate)
            }
    }

    private fun pegarOTextoDoEditTextNicknameEPorComoNickDoUsuario(et: EditText): String {
        nickname = et.text.toString()
        return nickname
    }


    private fun pegarOTextoDoEditTextNicknameEPorComoNumeroDoUsuario(et: EditText): String {
        numero = et.text.toString()
        return numero
    }

    private fun filtrarOQueFoiClicadoNoChipGroup(chipGroup: ChipGroup): List<String> {
        return chipGroup.children
            .filter { (it as Chip).isChecked }
            .map { (it as Chip).text.toString() }.toList()
    }

}