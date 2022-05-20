package com.example.findmysquad.View

import android.app.TimePickerDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.ViewModel.EditProfileViewModel
import com.example.findmysquad.databinding.ActivityEditProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class EditProfileActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private var savedHour = 0
    private var savedMinute = 0
    private lateinit var binding: ActivityEditProfileBinding
    private val model by inject<EditProfileViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "Editar Perfil"
            this.setDisplayHomeAsUpEnabled(true)
        }



        CoroutineScope(Dispatchers.IO).launch {
            binding.progressBar2.visibility = View.VISIBLE
            model.recuperarDadosUsuario(binding.etNick, binding.etEmailProfile, binding.img, binding.progressBar2)
        }

        CoroutineScope(Dispatchers.Main).launch {
            model.clock(this@EditProfileActivity, binding.btnHor)
        }

        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.atualizarCadastroUsuario(
                    binding.etNick,
                    binding.etEmailProfile,
                    binding.chipGroup,
                    binding.chipGroup2,
                    this@EditProfileActivity
                )
            }
        }


        binding.btnDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                model.deletarConta(this@EditProfileActivity)
            }

        }

        uparImageParaDB()

    }

    override fun onStop() {
        super.onStop()
        CoroutineScope(Dispatchers.IO).launch {
            model.receberFotoEPorNoBancoDeDados()
        }

    }

    private fun uparImageParaDB() {
        val getImageFromGalley = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            binding.img.setImageURI(uri)
            atualizarFotoParaStorage(FirebaseFeatures.getAuth().currentUser?.uid.toString(), uri!!)
        }
        binding.img.setOnClickListener {
            getImageFromGalley.launch("image/*")
        }
    }

    private fun atualizarFotoParaStorage(toString: String, uri: Uri) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                model.uparImgEscolhidaParaOBanco(toString, uri)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        "Sucesso ao atualizar a foto",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        e.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
    }
}