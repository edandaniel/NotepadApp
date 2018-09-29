package com.edandaniel.notepadapp.repository

import com.edandaniel.notepadapp.api.getNoteAPI
import com.edandaniel.notepadapp.model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteRepository{
    fun findAll(
            onComplete:(List<Note>?) -> Unit,
            onError:(Throwable?) -> Unit
    ){
        getNoteAPI()
                .findAll()
                .enqueue(object :Callback<List<Note>>{
                    override fun onFailure(call: Call<List<Note>>?, t: Throwable?) {
                        onError(t)
                    }

                    override fun onResponse(call: Call<List<Note>>?, response: Response<List<Note>>?) {
                        onComplete(response?.body())
                    }
                })
    }
}