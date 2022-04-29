package com.example.findmysquad.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.Model.Objects.FirebaseFeatures
import com.example.findmysquad.R
import com.example.findmysquad.View.Adapter.ReyclerMyRe
import com.example.findmysquad.ViewModel.MyReqRepViewModel
import com.example.findmysquad.databinding.ActivityMyRequicisoesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyRequicisoesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyRequicisoesBinding
    private val model by inject<MyReqRepViewModel>()

    private var lista = ArrayList<ModelRequisicoes>()
    private lateinit var adapter: ReyclerMyRe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRequicisoesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recuperarDadosDeUmUnicoUsuarioEConfigurarRecyclerView()


    }


    private fun recuperarDadosDeUmUnicoUsuarioEConfigurarRecyclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            model.receberDadosDoUnicoUsuario(FirebaseFeatures.getAuth().currentUser?.uid.toString())
        }
        CoroutineScope(Dispatchers.Main).launch {
            model.listaObserve.observe(this@MyRequicisoesActivity) {
                lista = it
                adapter = ReyclerMyRe(lista)
                binding.recyclerMyRe.adapter = adapter
                binding.recyclerMyRe.layoutManager = LinearLayoutManager(this@MyRequicisoesActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_profile -> {
                startActivity(Intent(this, EditProfileActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}