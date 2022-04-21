package com.example.findmysquad.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.R
import com.example.findmysquad.View.Adapter.RecyclerMainAdapter
import com.example.findmysquad.ViewModel.TelaPrincipalViewModel
import com.example.findmysquad.databinding.ActivityTelaPrincipalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val model by inject<TelaPrincipalViewModel>()

    private val listaRequisicoes = ArrayList<ModelRequisicoes>()
    private lateinit var adapter: RecyclerMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnFab()
        receberData()
    }

    private fun btnFab() {
        binding.fabMain.setOnClickListener {
            startActivity(Intent(this, AddNewRequisicao::class.java))
        }
    }

    private fun receberData() {
        CoroutineScope(Dispatchers.IO).launch {
            model.recuperarDadosDeRequisicoes()
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