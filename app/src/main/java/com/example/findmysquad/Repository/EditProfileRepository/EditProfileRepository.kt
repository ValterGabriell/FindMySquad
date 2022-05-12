package com.example.findmysquad.Repository.EditProfileRepository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.children
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Methods
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity
import com.example.findmysquad.View.TelaPrincipalActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileRepository : IEditProfileRepository {

    private val auth = FirebaseFeatures.getAuth().currentUser
    private var timerDate: String = ""
    private var nickname: String = ""
    private val imgRef = FirebaseFeatures.getStorage()


    override suspend fun recuperarDadosUsuario(
        editTextNickname: EditText,
        editTextEmail: EditText,
        img: ImageView
    ) {
        val email = auth?.email.toString()
        val nickname = auth?.displayName.toString()
        val urlFoto = auth?.photoUrl

        editTextEmail.setText(email)
        editTextNickname.setText(nickname)
        CoroutineScope(Dispatchers.Main).launch {
            Picasso.get().load(urlFoto).into(img)
        }
    }

    override suspend fun uparAImagemEscolhidaParaOBancoDeDados(filename: String, uri: Uri) {
        imgRef.child("images/$filename").putFile(uri)
    }


    override suspend fun deletarConta(context: Context) {
        FirebaseFeatures.getDatabase().collection(Texts.USERS_NAME).document(auth?.uid.toString())
            .delete().addOnSuccessListener {
                FirebaseFeatures.getAuth().signOut()
                context.startActivity(Intent(context, LogarActivity::class.java))
                Toast.makeText(context, "Conta deletada", Toast.LENGTH_LONG).show()
            }
        FirebaseFeatures.getStorage().child("images/${FirebaseFeatures.getAuth().currentUser?.uid}")
            .delete()
        FirebaseFeatures.getAuth().currentUser?.delete()
        FirebaseFeatures.getDatabase().collection(Texts.REQUISICAO_NAME)
            .document(auth?.uid.toString()).delete()
    }

    override fun abrirOTimerPickerEConfigurarAHora(context: Context) {
        timerDate = Methods.configTimerPicker(context)
    }

    private fun pegarOTextoDoEditTextNicknameEPorComoNickDoUsuario(et: EditText): String {
        nickname = et.text.toString()
        return nickname
    }

    private fun filtrarOQueFoiClicadoNoChipGroup(chipGroup: ChipGroup): List<String> {
        return chipGroup.children
            .filter { (it as Chip).isChecked }
            .map { (it as Chip).text.toString() }.toList()
    }


    override suspend fun atualizarPerfil(
        et: EditText,
        etEmail : EditText,
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
                "photo" to ""
            )
            /**
             * upar perfil do usuario
             */
            atualizarColecaoNoFirebase(et, context, profileMap)

        }
    }

    private fun atualizarColecaoNoFirebase(et: EditText, context: Context, profileMap: Any) {
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


}