package com.edandaniel.notepadapp.view.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edandaniel.notepadapp.model.Note
import com.edandaniel.notepadapp.model.ResponseStatus
import com.edandaniel.notepadapp.repository.NoteRepository

class FormViewModel: ViewModel() {
    val responseStatus : MutableLiveData<ResponseStatus> = MutableLiveData()
    val noteRepository = NoteRepository()

    fun save(title:String, description:String){
        val note = Note(null, title, description)
        noteRepository.save(note,
                onComplete = {
                    responseStatus.value = ResponseStatus(true,"Data saved with success")
                },
                onError = {
                    responseStatus.value = ResponseStatus(true,it?.message!!)
                })
    }
}