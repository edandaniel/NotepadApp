package com.edandaniel.notepadapp.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edandaniel.notepadapp.model.Note
import com.edandaniel.notepadapp.repository.NoteRepository

class MainViewModel: ViewModel(){
    val noteRepository = NoteRepository()

    var notes: MutableLiveData<List<Note>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun findAll(){
        isLoading.value = true
        noteRepository
            .findAll(
                onComplete = {
                    isLoading.value = false
                    notes.value = it
                },onError = {
                    isLoading.value = false
                    notes.value = mutableListOf()
                })
    }
}