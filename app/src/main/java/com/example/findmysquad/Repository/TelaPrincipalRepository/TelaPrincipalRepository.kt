package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context
import android.content.Intent
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.View.LogarActivity

class TelaPrincipalRepository : ITelaPrincipalRepository {
    override suspend fun signOut(context: Context) {
        FirebaseFeatures.getAuth().signOut()
        context.startActivity(Intent(context, LogarActivity::class.java))
    }

}