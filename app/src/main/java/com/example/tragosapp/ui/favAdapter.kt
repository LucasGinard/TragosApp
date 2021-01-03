package com.example.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tragosapp.R
import com.example.tragosapp.base.baseViewHolder
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.databinding.ItemTragosBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tragos.view.*

class favAdapter (private val context: Context, private val tragosLista: List<TragosEntity>, private val itemClickListener:onTragoClickListener2):
    RecyclerView.Adapter<baseViewHolder<*>>(){

    interface onTragoClickListener2{
        fun onTragoClick(trago: TragosEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): baseViewHolder<*> {
        return mainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tragos,parent,false))
    }

    override fun onBindViewHolder(holder: baseViewHolder<*>, position: Int) {
        when(holder){
            is mainViewHolder -> holder.bind(tragosLista[position],position)
        }
    }

    override fun getItemCount(): Int {
        return tragosLista.size
    }


    fun getTragoPo(position:Int): TragosEntity {
        return tragosLista[position]
    }


    inner class mainViewHolder(itemView: View): baseViewHolder<TragosEntity>(itemView){
        val binding = ItemTragosBinding.bind(itemView)
        override fun bind(item: TragosEntity, position: Int) {
            Picasso.get().load(item.imagen).into(binding.imTrago)
            binding.tvTitutlo.text = item.nombre
            binding.tvDescripcion.text = item.descripcion
            itemView.setOnClickListener {itemClickListener.onTragoClick(item)}
        }
    }
}