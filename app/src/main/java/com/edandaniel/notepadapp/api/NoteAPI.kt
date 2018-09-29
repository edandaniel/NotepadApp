package com.edandaniel.notepadapp.api

import com.edandaniel.notepadapp.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NoteAPI{
    @GET("/note")
    fun findAll(): Call<List<Note>>

    @GET("/note/title/{title}")
    fun findByTitle(@Path( "title") title:String): Call<List<Note>>

    @POST("/note")
    fun save(@Body note:Note): Call<Note>

    @DELETE("/note/{id}")
    fun delete(@Path("id") id:String): Call<Void>
}