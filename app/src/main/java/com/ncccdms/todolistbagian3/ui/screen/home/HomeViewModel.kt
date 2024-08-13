package com.ncccdms.todolistbagian3.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskData
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import com.ncccdms.todolistbagian3.domain.model.Response.Loading
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import com.ncccdms.todolistbagian3.domain.repository.ReloadUserResponse
import com.ncccdms.todolistbagian3.domain.repository.RevokeAccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repository.reloadUser()
    }

    val isEmailVerified get() = repository.currentUser?.isEmailVerified ?: false

    fun signOut() = repository.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repository.revokeAccess()
    }

    val tasks = TaskData.dummytask // Use dummy data here
    val progress = calculateProgress(tasks) // Calculate progress based on dummy data


    fun calculateProgress(tasks: List<Task>): Float {
        val totalTasks = tasks.size
        val completedTasks = tasks.count { it.taskStatus == TaskStatus.Finished }
        return if (totalTasks == 0) 0f else completedTasks.toFloat() / totalTasks.toFloat()
    }
}