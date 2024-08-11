package com.ncccdms.todolistbagian3.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.ncccdms.todolistbagian3.domain.model.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AuthRepository {
    val currentUser:FirebaseUser?

    suspend fun signIn(email:String, password: String):SignInResponse

    suspend fun signUp(email:String, password: String):SignUpResponse

    suspend fun resetPassword(email: String):SendPasswordResetEmailResponse

    suspend fun sendEmailVerification():SendEmailVerificationResponse

    suspend fun reloadUser():ReloadUserResponse

    suspend fun revokeAccess():RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope):AuthStateResponse

    fun signOut()
}