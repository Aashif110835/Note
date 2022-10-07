package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.note.adapter.DeleteNote
import com.example.note.adapter.NoteAdapter

class MainActivity : AppCompatActivity(){
    lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_all,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.deleteAll->{
              deleteAll()
          }
          R.id.fav->{
              navController.navigate(R.id.favourites)
          }
      }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
       val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete")
        builder.setMessage("Delete all note?")
        builder.setPositiveButton("Yes") { _, _ ->
            noteViewModel.deleteAll()
        }
        builder.setNegativeButton("No") { _, _ -> }
            .create().show()
    }
}