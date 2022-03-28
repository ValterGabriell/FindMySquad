package com.example.findmysquad.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap as HashMap1

class TelaPrincipalViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    suspend fun createAProfileData(game: List<String>) {
        val profileMap = hashMapOf(
            "id" to auth.currentUser?.uid,
            "jogos" to game
        )

        db.collection("User").document(auth.currentUser?.uid.toString()).set(profileMap)
            .addOnCompleteListener {
                println("Debug -> Criado com sucesso")
            }
    }

    suspend fun createGamesData() {
        val r6 = configGame( listOf("PC, XBOX, PS"),5)
        val cs = configGame( listOf("PC"),5)
        val vava = configGame(listOf("PC"),5)




        db.collection("Games").document("Rainbow Six Siege").set(r6)
        db.collection("Games").document("Counter Strike").set(cs)
        db.collection("Games").document("Valorant").set(vava)

    }

    private fun configGame( listaPlataformas : List<String>, qtdPlayers:Int): kotlin.collections.HashMap<String, Any> {

        val profileGameR6 = hashMapOf(
            "qtdPlayers" to qtdPlayers,
            "plataformas" to listaPlataformas
        )

        return profileGameR6

    }

}
