package com.example.findmysquad.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TelaPrincipalViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    suspend fun createAProfileData(game:List<String>){
        val profileMap = hashMapOf(
            "id" to auth.currentUser?.uid,
            "jogos"  to game
        )

        db.collection("User").document(auth.currentUser?.uid.toString()).set(profileMap).addOnCompleteListener {
            println("Debug -> Criado com sucesso")
        }
    }


}