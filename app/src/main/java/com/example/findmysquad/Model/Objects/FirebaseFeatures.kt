package com.example.findmysquad.Model.Objects

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object FirebaseFeatures {

    fun getAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    fun getDatabase() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    fun getStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

}