package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.note.NoteEntity
import com.example.note.NoteViewModel
import com.example.note.R
import com.example.note.adapter.DeleteNote
import com.example.note.adapter.NoteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Home : Fragment(), DeleteNote{

    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    val noteViewModel: NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fab = view.findViewById(R.id.floatingActionButton)
        recyclerView = view.findViewById(R.id.recyclerView)


        noteViewModel.allNote.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = NoteAdapter(it,this,this)

        })
        fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_to_add)
        }
    }

    override fun onItemClicked(noteEntity: NoteEntity) {
        noteViewModel.delNote(noteEntity)
    }

   fun onFavItemClicked(idd: Int, like: Boolean, title: String, description: String) {
       val noteEntity = NoteEntity(
           id=0,
           title=title,
           description=description,
           isLike = like)

       noteEntity.id=idd
       noteViewModel.addLike(noteEntity)

    }

}