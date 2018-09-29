package com.edandaniel.notepadapp.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edandaniel.notepadapp.R
import com.edandaniel.notepadapp.model.Note
import kotlinx.android.synthetic.main.note_item.view.*

class MainListAdapter(
        val context: Context,
        val notes: List<Note>,
        val listener: (Note) -> Unit,
        val listenerDelete: (Note) -> Unit) : RecyclerView.Adapter<MainListAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder?.let {
            holder.bindView(note, listener, listenerDelete)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Note,
                     listener: (Note) -> Unit,
                     listenerDelete: (Note) -> Unit) = with(itemView) {
            tvTitle.text = note.title
            tvDescription.text = note.description

            /*ivDelete.setOnClickListener {
                listenerDelete(note)
            }*/
            setOnClickListener { listener(note) }
        }
    }
}