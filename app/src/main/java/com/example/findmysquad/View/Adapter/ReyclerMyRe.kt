package com.example.findmysquad.View.Adapter

import android.annotation.SuppressLint
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
        @SuppressLint("SetTextI18n")
        fun bind(modelRequisicoes: ModelRequisicoes) {
            itemView.findViewById<TextView>(R.id.txtGame).text = modelRequisicoes.game.toString()
            itemView.findViewById<TextView>(R.id.txtHorario).text =
                "Horário de partida: ${modelRequisicoes.horario}"
            itemView.findViewById<TextView>(R.id.txtPlataform).text =
                modelRequisicoes.plataforma.toString()
            itemView.findViewById<TextView>(R.id.txtUsername).text = modelRequisicoes.user
            val img = itemView.findViewById<ImageView>(R.id.imageView2)
            Picasso.get().load(modelRequisicoes.photoUri).into(img)



        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderR {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_main_adapter_my, parent, false)
        return MyViewHolderR(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderR, position: Int) {
        holder.bind(listaR[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position, listaR[position].idField)
        }
    }

    override fun getItemCount(): Int {
        return listaR.size
    }

    var onItemClick: ((Int, String) -> Unit)? = null

}