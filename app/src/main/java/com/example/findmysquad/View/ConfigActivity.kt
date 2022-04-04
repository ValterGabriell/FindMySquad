package com.example.findmysquad.View

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findmysquad.R
import com.example.findmysquad.databinding.ActivityConfigBinding
import com.example.findmysquad.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        finalizarCadastro()

    }

    private fun finalizarCadastro() {
        binding.btnConfirm.setOnClickListener {
            verificarForm()
        }
    }

    private fun verificarForm() {
        val nickname: String = binding.etNick.text.toString()

        getRadioNames()
        configHorario()
        getPlataromas()


        val profileMap = hashMapOf(
            "nickname" to nickname,
            "jogos" to getRadioNames(),
            "plataformas" to getPlataromas(),
            "horario" to configHorario()

        )

        db.collection("User").document(auth.currentUser?.uid.toString()).set(profileMap)
        startActivity(Intent(this, TelaPrincipalActivity::class.java))

    }


    private fun getPlataromas() : List<String>{
        val listaPlata = mutableListOf<String>()

        if (binding.radioPC.isChecked) {
            listaPlata.add(binding.radioPC.text.toString())
        } else if (binding.radioPS.isChecked) {
            listaPlata.add((binding.radioPS.text.toString()))
        } else if (binding.radioXBOX.isChecked) {
            listaPlata.add(binding.radioXBOX.text.toString())
        }

        return listaPlata
    }

    private fun configHorario() : String {
        val calendar = Calendar.getInstance()
        binding.btnClock.setOnClickListener {
            val timePickerDialog = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                binding.btnClock.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(
                this,
                timePickerDialog,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        return SimpleDateFormat("HH:mm").format(calendar.time)

    }

    private fun getRadioNames()  : List<String>{
        val lista = mutableListOf<String>()

      binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
          when(checkedId){

          }
      }

        return lista
    }

}
