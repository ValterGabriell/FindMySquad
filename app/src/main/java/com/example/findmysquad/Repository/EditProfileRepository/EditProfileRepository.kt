package com.example.findmysquad.Repository.EditProfileRepository

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.View.LogarActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileRepository : IEditProfileRepository {

    private val auth = FirebaseFeatures.getAuth().currentUser

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


    }


}