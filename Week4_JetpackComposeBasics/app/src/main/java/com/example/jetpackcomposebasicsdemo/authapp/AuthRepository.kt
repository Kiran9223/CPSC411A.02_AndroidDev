package com.example.jetpackcomposebasicsdemo.authapp

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

object AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    val isUserLoggedIn: Boolean
        get() = auth.currentUser != null

    //signup
    suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    //sign in
    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //signout
    fun signOut() {
        auth.signOut()
    }

    //get JWT token
    suspend fun getIdToken(): String? {
        return try {
            currentUser?.getIdToken(false)?.await()?.token
        }catch(e: Exception) {
            null
        }
    }

}