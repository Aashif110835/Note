package com.example.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.adapter.FavAdapter
import com.example.note.database.NoteDatabase

class Favourites : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: List<NoteEntity>
    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        list = listOf()

        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        noteViewModel.allFavNote.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = FavAdapter(it,this,this)
        })
    }

    fun onItemClicked(noteEntity: NoteEntity) {
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

