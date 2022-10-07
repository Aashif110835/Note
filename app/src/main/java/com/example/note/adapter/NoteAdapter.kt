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
import com.example.note.NoteEntity
import com.example.note.R
import com.example.note.fragments.Home

class NoteAdapter(
    private val noteList: List<NoteEntity>,
    private val listener: DeleteNote,
    private val fragment : Home,
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.title)
        val description:TextView = view.findViewById(R.id.description)
        val delete: ImageView = view.findViewById(R.id.delete)
        val rowBackground:CardView = view.findViewById(R.id.rowBackground)
        val like:ImageView = view.findViewById(R.id.like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val data = noteList[position]
        holder.title.text = data.title
        holder.description.text = data.description
        holder.delete.setOnClickListener {
            listener.onItemClicked(data)
        }

        if (data.isLike){
            holder.like.setImageResource(R.drawable.ic_like)
        }else{
            holder.like.setImageResource(R.drawable.ic_unlike)
        }
        holder.title.setOnClickListener {

            val bundle =Bundle().apply {
                putString("tittle",data.title)
                putString("description",data.description)
                putString("id",data.id.toString())
            }

            holder.title.findNavController().navigate(R.id.action_home_to_update,bundle)
        }
        holder.like.setOnClickListener {

            if (data.isLike){
                noteList[position].id?.let { it1 -> fragment.onFavItemClicked(it1,false,noteList[position].title,noteList[position].description) }
            }else{
                noteList[position].id?.let { it1 -> fragment.onFavItemClicked(it1,true,noteList[position].title,noteList[position].description) }

            }
        }


    }
    override fun getItemCount(): Int {
        return  noteList.size
    }
}
interface DeleteNote{
    fun onItemClicked(noteEntity: NoteEntity)
}