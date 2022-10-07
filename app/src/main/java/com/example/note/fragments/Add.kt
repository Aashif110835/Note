package com.example.note.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.note.NoteEntity
import com.example.note.NoteViewModel
import com.example.note.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Add : Fragment() {

    lateinit var title: EditText
    private lateinit var description: EditText
    lateinit var save: FloatingActionButton
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.description)
        save = view.findViewById(R.id.floatingActionButton)


        save.setOnClickListener {
            val data = NoteEntity(
                0,
                title.text.toString(),
                description.text.toString()
            )

            noteViewModel.addNote(data)
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_add_to_home)
        }
    }

}