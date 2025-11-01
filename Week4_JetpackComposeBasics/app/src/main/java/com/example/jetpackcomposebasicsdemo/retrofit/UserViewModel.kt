package com.example.jetpackcomposebasicsdemo.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    init {
        fetchUsers()
    }



    fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null


            try {

                val value = RetrofitInstance.api.getUsers()

                _users.value = value

            } catch (e: Exception) {
                _errorMessage.value = "Error fetching users: ${e.message}"
            } finally {
                _isLoading.value= false
            }
        }
    }
}