package com.example.findmysquad.Repository.TelaPrincipalRepository

import android.content.Context

interface ITelaPrincipalRepository {
    suspend fun signOut(context: Context)

}