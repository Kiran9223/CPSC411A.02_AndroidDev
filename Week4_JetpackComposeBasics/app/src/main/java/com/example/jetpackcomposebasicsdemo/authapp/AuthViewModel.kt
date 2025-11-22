package com.example.jetpackcomposebasicsdemo.authapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebasicsdemo.AuthApp
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle: AuthState()
    object Loading: AuthState()
    data class Success(val user: FirebaseUser): AuthState()
    data class Error(val message: String): AuthState()
}

class AuthViewModel: ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        val currentUser = AuthRepository.currentUser
        if(currentUser != null) {
            _authState.value = AuthState.Success(currentUser)
        }
    }

    fun signIn(email: String, password: String) {
        if(email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email or must cannot be empty")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading

            AuthRepository.signIn(email, password)
                .onSuccess { user ->
                    _authState.value = AuthState.Success(user)
                }
                .onFailure { exception ->
                    _authState.value = AuthState.Error("Theres an error ${exception}")
                }

        }


    }

    fun signUp(email: String, password: String) {
        if(email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email or password cannot be empty")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading

            AuthRepository.signIn(email, password)
                .onSuccess {
                    user->
                    _authState.value = AuthState.Success(user)
                }
                .onFailure { exception ->
                    _authState.value= AuthState.Error("Theres an error ${exception}")
                }
        }
    }

    fun signOut() {
        AuthRepository.signOut()
        _authState.value = AuthState.Idle

    }
}