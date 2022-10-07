package com.example.note.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.note.Favourites
import com.example.note.NoteEntity
import com.example.note.R
import com.example.note.database.NoteDatabase
import com.example.note.fragments.Home

class FavAdapter(
    var list: List<NoteEntity>,
    private val listener: Favourites,
    private val fragment: Favourites):RecyclerView.Adapter<FavAdapter.ViewHolder>() {


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val delete: ImageView = itemView.findViewById(R.id.delete)
        val like:ImageView = itemView.findViewById(R.id.like)
        val rowBackground: CardView = itemView.findViewById(R.id.rowBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.like_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = list[position]
        holder.title.text = item.title
        holder.description.text = item.description
        holder.delete.setOnClickListener {
            listener.onItemClicked(item)
        }

        if (item.isLike){
            holder.like.setImageResource(R.drawable.ic_like)
        }else{
            holder.like.setImageResource(R.drawable.ic_unlike)
        }
        holder.rowBackground.setOnClickListener {

            val bundle = Bundle().apply {
                putString("tittle",item.title)
                putString("description",item.description)
                putString("id",item.id.toString())
            }

        }
        holder.like.setOnClickListener {

            if (item.isLike){
                list[position].id?.let { it1 -> fragment.onFavItemClicked(it1,false,list[position].title,list[position].description) }
            }else{
                list[position].id?.let { it1 -> fragment.onFavItemClicked(it1,true,list[position].title,list[position].description) }

            }
        }

    }
    override fun getItemCount(): Int {
       return list.size
    }
}