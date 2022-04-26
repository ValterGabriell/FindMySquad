package com.example.findmysquad.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.R
import com.squareup.picasso.Picasso

class ReyclerMyRe(private val listaR: ArrayList<ModelRequisicoes>) :
    RecyclerView.Adapter<ReyclerMyRe.MyViewHolderR>() {

    inner class MyViewHolderR(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(modelRequisicoes: ModelRequisicoes) {
            itemView.findViewById<TextView>(R.id.txtGame).text = modelRequisicoes.game.toString()
            itemView.findViewById<TextView>(R.id.txtHorario).text = modelRequisicoes.horario
            itemView.findViewById<TextView>(R.id.txtPlataform).text =
                modelRequisicoes.plataforma.toString()
            itemView.findViewById<TextView>(R.id.txtUsername).text = modelRequisicoes.user
            val img = itemView.findViewById<ImageView>(R.id.imageView2)
            Picasso.get().load(modelRequisicoes.photoUri).into(img)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderR {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_main_adapter, parent, false)
        return MyViewHolderR(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderR, position: Int) {
        holder.bind(listaR[position])
    }

    override fun getItemCount(): Int {
        return listaR.size
    }

}