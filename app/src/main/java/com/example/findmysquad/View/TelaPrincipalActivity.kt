package com.example.findmysquad.View

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.R
import com.example.findmysquad.View.Adapter.RecyclerMainAdapter
import com.example.findmysquad.ViewModel.TelaPrincipalViewModel
import com.example.findmysquad.databinding.ActivityTelaPrincipalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        binding.recyclerMain.visibility = View.GONE
        binding.shimmer.apply {
            visibility = View.VISIBLE
            this.startShimmerAnimation()
        }


        supportActionBar?.apply {
            title = "Requisições"
        }
        btnFab()

    }

    override fun onStart() {
        super.onStart()
        configRecycler()
    }

    private fun configRecycler() {
        CoroutineScope(Dispatchers.IO).launch {
            model.configurarDados(FirebaseFeatures.getAuth().currentUser?.uid.toString())

        }
        CoroutineScope(Dispatchers.Main).launch {
            model.listaRequisicoes.observe(this@TelaPrincipalActivity) {
                listaRequisicoesMain = it
                listaRequisicoesMain.reverse()

                binding.shimmer.apply {
                    this.stopShimmerAnimation()
                    visibility = View.GONE
                }
                binding.recyclerMain.visibility = View.VISIBLE
                adapter = RecyclerMainAdapter(listaRequisicoesMain)
                binding.recyclerMain.adapter = adapter
                binding.recyclerMain.layoutManager = LinearLayoutManager(this@TelaPrincipalActivity)
                adapter.onItemClick = { position, id ->
                    model.recuperarNumeroCelular(this@TelaPrincipalActivity, id)
                }
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
                startActivity(Intent(this, MyRequicisoesActivity::class.java))
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