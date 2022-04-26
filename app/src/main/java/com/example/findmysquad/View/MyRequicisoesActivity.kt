package com.example.findmysquad.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
}