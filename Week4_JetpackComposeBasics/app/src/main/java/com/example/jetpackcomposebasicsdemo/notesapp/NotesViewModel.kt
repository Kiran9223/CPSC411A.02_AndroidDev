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

    }

    fun updateNote(noteId: String, newTitle: String, newBody: String) {

    }

    fun searchNotes(query: String) {
        _searchQuery.value = query

        if(query.isBlank()) {
            fetchAllNotes()
            return
        }


    }



}