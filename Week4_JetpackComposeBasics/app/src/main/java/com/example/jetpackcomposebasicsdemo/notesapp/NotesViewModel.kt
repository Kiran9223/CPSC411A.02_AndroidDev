package com.example.jetpackcomposebasicsdemo.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    init {
        fetchAllNotes()
    }

    fun fetchAllNotes() {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = NotesRetrofitInstance.api.getAllNotes()
                _notes.value = response
            } catch (e: Exception) {
                print("Error fetching notes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createNote(title: String, body: String) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val noteRequest = NoteRequest(
                    title = title,
                    body = body,
                    userId = "1"
                )

                val createdNote = NotesRetrofitInstance.api.createNote(noteRequest)

                _notes.value = listOf(createdNote) + _notes.value

            } catch (e: Exception){
                print("Error while creating notes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateNote(noteId: String, newTitle: String, newBody: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try{
                val updatedNote = NoteRequest(
                    title = newTitle,
                    body = newBody,
                    userId = "1"
                )

                val updatedNoteResponse = NotesRetrofitInstance.api.updateNote(noteId, updatedNote)

                _notes.value = _notes.value.map {
                    note ->
                    if(note.id == noteId) updatedNoteResponse else note
                }


            } catch (e: Exception) {
                print("Error while updating notes: ${e.message}")

            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchNotes(query: String) {
        _searchQuery.value = query

        if(query.isBlank()) {
            fetchAllNotes()
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try{
                val filtered = NotesRetrofitInstance.api.searchNotes(
                    title = query,
                    body = null
                )
                _notes.value = filtered
            } catch (e: Exception){
                print("Error while searching: ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }


    }



}