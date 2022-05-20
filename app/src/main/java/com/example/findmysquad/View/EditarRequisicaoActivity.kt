package com.example.findmysquad.View

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.ViewModel.EditarRequisicaoViewModel
import com.example.findmysquad.databinding.ActivityEditarRequisicaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class EditarRequisicaoActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityEditarRequisicaoBinding
    private val model by inject<EditarRequisicaoViewModel>()
    private var savedHour = 0
    private var savedMinute = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarRequisicaoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {
            model.clock(this@EditarRequisicaoActivity, binding.btnHor)
        }


        val idFieldUpdateDelete = intent?.extras?.getString("idField")
        binding.btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.updateRequisition(binding.chipGame, binding.chipPlata, idFieldUpdateDelete!!)
                val intent =
                    Intent(this@EditarRequisicaoActivity, TelaPrincipalActivity::class.java)
                startActivity(intent)
            }
        }

        val idField = intent?.extras?.getString("idField")
        binding.btnDeleteRequisicao.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.deleteRequisicao(this@EditarRequisicaoActivity, idField!!)
            }
        }


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
    }
}