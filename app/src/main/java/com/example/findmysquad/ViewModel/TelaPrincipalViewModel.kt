package com.example.findmysquad.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findmysquad.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap as HashMap1

class TelaPrincipalViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    suspend fun createGamesData(context: Context) {
        val array = context.resources.getStringArray(R.array.gamesArray)
        val qtdPlayres = context.resources.getStringArray(R.array.qtdPlayers)
        val plataforma = context.resources.getStringArray(R.array.Plataformas)

        for ( i in array.indices){
            for (x in qtdPlayres.indices) {
                for (y in plataforma.indices) {
                    val profile = hashMapOf(
                        "Titulo" to array[i],
                        "Quantidade de players" to qtdPlayres[x],
                        "Plataformas" to plataforma[y]
                    )
                    db.collection("Games").document(array[i]).set(profile)
                }
            }
        }

    }

}
