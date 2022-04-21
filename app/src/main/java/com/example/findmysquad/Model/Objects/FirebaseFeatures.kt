package com.example.findmysquad.Model.Objects

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

object FirebaseFeatures {

    fun getAuth(): FirebaseAuth {
        return Firebase.auth
    }

    fun getDatabase(): FirebaseFirestore {
        return Firebase.firestore
    }

    fun getStorage(): StorageReference {
        return Firebase.storage.reference
    }

}