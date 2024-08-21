package com.ncccdms.todolistbagian3.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.domain.model.Response.Failure
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import com.ncccdms.todolistbagian3.domain.repository.SignUpResponse
import com.ncccdms.todolistbagian3.domain.repository.getUsernameResponse
import com.ncccdms.todolistbagian3.domain.repository.saveUserDetailsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override val currentUser get() = auth.currentUser

    override suspend fun signUp(email: String, password: String, username: String): SignUpResponse {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: return Failure(Exception("User ID not found"))
            saveUserDetails(userId, username)

            result.user?.sendEmailVerification()?.await()

            Success(true)
        } catch (e: FirebaseAuthUserCollisionException) {
            Failure(Exception("Email already in use"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Failure(Exception("Invalid email or password"))
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun saveUserDetails(userId: String, username: String): saveUserDetailsResponse {
        return try {
            val userDoc = firestore.collection("users").document(userId)
            userDoc.set(mapOf("username" to username, "wee" to userId)).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getUsername(userId: String): getUsernameResponse {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            val username = userDoc.getString("username") ?: ""
            Success(username)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun sendEmailVerification() = try {
        auth.currentUser?.sendEmailVerification()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun signIn(email: String, password: String) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun reloadUser() = try {
        auth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun resetPassword(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)
}