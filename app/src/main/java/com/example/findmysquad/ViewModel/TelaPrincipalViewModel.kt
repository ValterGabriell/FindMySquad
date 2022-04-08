package com.example.findmysquad.ViewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.findmysquad.R
import com.example.findmysquad.View.LogarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap as HashMap1

class TelaPrincipalViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun signOut(context: Context){
        auth.signOut()
        context.startActivity(Intent(context, LogarActivity::class.java))
    }


}
