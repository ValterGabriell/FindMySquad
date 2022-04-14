package com.example.findmysquad.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.R
import com.example.findmysquad.View.Adapter.RecyclerMainAdapter
import com.example.findmysquad.ViewModel.TelaPrincipalViewModel
import com.example.findmysquad.databinding.ActivityTelaPrincipalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTelaPrincipalBinding
    private val model : TelaPrincipalViewModel by viewModels()
    private val listaRequisicoes = ArrayList<ModelRequisicoes>()
    private lateinit var adapter : RecyclerMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnFab()



    }

    private fun btnFab(){
        binding.fabMain.setOnClickListener {
            startActivity(Intent(this, AddNewRequisicao::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.id_profile -> {

            }

            R.id.id_logout ->{
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