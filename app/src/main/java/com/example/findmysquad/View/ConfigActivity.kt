package com.example.findmysquad.View

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.ConfigViewModel
import com.example.findmysquad.databinding.ActivityConfigBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.collection.LLRBEmptyNode
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private val model by inject<ConfigViewModel>()
    private val register = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        it?.let {
            binding.img.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnHor.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.clock(this@ConfigActivity)
            }
        }

        /**
         * Método para recuperar a imagem
         * */

        binding.img.setOnClickListener {
            register.launch("image/*")
        }

        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                model.validateForm(
                    binding.etNick,
                    binding.chipGroup,
                    binding.chipGroup2,
                    this@ConfigActivity,
                    binding.img
                )
            }
        }

    }

    override fun onBackPressed() {

    }
}
