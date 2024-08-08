package com.ncccdms.todolistbagian3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel(){
    init {
        getAuthState()
    }

    fun getAuthState() = repository.getAuthState(viewModelScope)

    val isEmailVerified get() = repository.currentUser?.isEmailVerified ?: false
}