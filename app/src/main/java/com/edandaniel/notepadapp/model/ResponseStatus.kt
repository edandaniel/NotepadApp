package com.edandaniel.notepadapp.model

data class ResponseStatus(
        val success: Boolean,
        val message: String? //possible to start with empty string too, or other stuff
)