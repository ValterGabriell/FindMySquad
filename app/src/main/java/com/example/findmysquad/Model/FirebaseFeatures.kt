package com.example.findmysquad.Model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseFeatures {

    fun getAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    fun getDatabase() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

}