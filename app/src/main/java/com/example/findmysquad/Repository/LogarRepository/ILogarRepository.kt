package com.example.findmysquad.Repository.LogarRepository

import android.content.Context
import android.widget.ProgressBar
import android.widget.Toast

interface ILogarRepository {
    suspend fun logarUsuário(email: String, senha: String, context: Context,progressBar: ProgressBar)

    suspend fun makeAToast(context: Context, text: String, time: Int)

}