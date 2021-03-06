package com.edandaniel.notepadapp.view.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.edandaniel.notepadapp.R
import com.edandaniel.notepadapp.model.Note
import com.edandaniel.notepadapp.view.form.FormActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private var adapter:MainListAdapter? = null

    val FORM_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)

        mainViewModel.notes.observe(this, notesObserver)
        mainViewModel.isLoading.observe(this, loadingObserver)

        mainViewModel.findAll()

        fab.setOnClickListener { view ->
            val formIntent = Intent(this,FormActivity::class.java)
            startActivityForResult(formIntent,FORM_REQUEST_CODE)
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }
    }


    private var loadingObserver = Observer<Boolean>{
        if(it == true){
            containerLoading.visibility = View.VISIBLE
        }else{
            containerLoading.visibility = View.GONE
        }
    }

    private var notesObserver = Observer<List<Note>>{
        fillList(it!!)
    }

    private fun fillList(notes:List<Note>){
        adapter = MainListAdapter(this,notes,{},{})
        rvNotes.adapter = adapter
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            FORM_REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK->{mainViewModel.findAll()}
                    Activity.RESULT_CANCELED->{
                        Toast.makeText(this,
                                "Canceled",
                                Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
