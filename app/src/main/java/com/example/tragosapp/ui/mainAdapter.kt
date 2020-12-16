package com.example.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tragosapp.R
import com.example.tragosapp.base.baseViewHolder
import com.example.tragosapp.data.model.Trago
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tragos.view.*

class mainAdapter(private val context:Context,private val tragosList: List<Trago>,private val itemClickListener:onTragoClickListenerFav):RecyclerView.Adapter<baseViewHolder<*>>(){

    interface onTragoClickListenerFav{
        fun onTragoClick(trago: Trago)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): baseViewHolder<*> {
        return mainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tragos,parent,false))
    }

    override fun onBindViewHolder(holder: baseViewHolder<*>, position: Int) {
        when(holder){
            is mainViewHolder -> holder.bind(tragosList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }


    inner class mainViewHolder(itemView:View):baseViewHolder<Trago>(itemView){
        override fun bind(item: Trago, position: Int) {
            Picasso.get().load(item.imagen).into(itemView.imTrago)
            itemView.tvTitutlo.text = item.nombre
            itemView.tvDescripcion.text = item.descripcion
            itemView.setOnClickListener {itemClickListener.onTragoClick(item)}
        }
    }
}