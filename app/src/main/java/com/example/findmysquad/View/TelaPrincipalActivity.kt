package com.example.findmysquad.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.Model.Objects.Texts
import com.example.findmysquad.R
import com.example.findmysquad.View.Adapter.RecyclerMainAdapter
import com.example.findmysquad.ViewModel.TelaPrincipalViewModel
import com.example.findmysquad.databinding.ActivityTelaPrincipalBinding
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val model by inject<TelaPrincipalViewModel>()
    private var listaRequisicoesMain = ArrayList<ModelRequisicoes>()
    private lateinit var adapter: RecyclerMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnFab()
        configRecycler()
    }

    private fun configRecycler() {
        CoroutineScope(Dispatchers.IO).launch {
            model.configurarDados()
        }
        CoroutineScope(Dispatchers.Main).launch {
            model.listaRequisicoes.observe(this@TelaPrincipalActivity) {
                listaRequisicoesMain = it
                adapter = RecyclerMainAdapter(listaRequisicoesMain)
                binding.recyclerMain.adapter = adapter
                binding.recyclerMain.layoutManager = LinearLayoutManager(this@TelaPrincipalActivity)
            }
        }
    }

    private fun btnFab() {
        binding.fabMain.setOnClickListener {
            startActivity(Intent(this, AddNewRequisicao::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_profile -> {
                startActivity(Intent(this, ConfigActivity::class.java))
            }

            R.id.id_logout -> {
                CoroutineScope(Dispatchers.Default).launch {
                    model.signOut(this@TelaPrincipalActivity)
                    startActivity(Intent(this@TelaPrincipalActivity, LogarActivity::class.java))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {

    }
}