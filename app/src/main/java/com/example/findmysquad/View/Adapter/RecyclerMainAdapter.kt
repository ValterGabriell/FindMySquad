package com.example.findmysquad.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findmysquad.Model.ModelRequisicoes
import com.example.findmysquad.R

class RecyclerMainAdapter(val list:List<ModelRequisicoes>) : RecyclerView.Adapter<RecyclerMainAdapter.MyViewHolder>() {

    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun onBind(modelRequisicoes: ModelRequisicoes) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_main_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    var onItemClick : ((Int) -> Unit)? = null


}