package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.note.NoteEntity
import com.example.note.NoteViewModel
import com.example.note.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Update : Fragment() {

    //val note by navArgs<UpdateArgs>()
    private val noteViewModel: NoteViewModel by viewModels()

    lateinit var updateTitle: EditText
    lateinit var updateDescription: EditText
    lateinit var update: FloatingActionButton
    var id=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        updateTitle = view.findViewById(R.id.updateTitle)
        updateDescription = view.findViewById(R.id.updateDescription)
        update = view.findViewById(R.id.floatingActionButton)

        updateTitle.setText(arguments?.getString("tittle"))
        updateDescription.setText(arguments?.getString("description"))
        id= arguments?.getString("id").toString()

        update.setOnClickListener {

            val title = updateTitle.text.toString()
            val description = updateDescription.text.toString()

            if(id != ""){
                val data = NoteEntity(
                    id.toInt(),
                    title = title,
                    description = description,
                )
                Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show()

                noteViewModel.updateNote(data)

                Navigation.findNavController(it).navigate(R.id.action_update_to_home)
            }
        }
    }
}
