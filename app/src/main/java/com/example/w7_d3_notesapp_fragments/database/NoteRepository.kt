package com.example.w7_d3_notesapp_fragments.database

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val getNotes: LiveData<List<Note>> = noteDao.getNotes()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

}