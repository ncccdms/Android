package com.ncccdms.todolistbagian3.ui.screen.task.list_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncccdms.todolistbagian3.domain.model.Response.Failure
import com.ncccdms.todolistbagian3.domain.model.Response.Loading
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.domain.model.TaskStatus
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import com.ncccdms.todolistbagian3.domain.repository.TasksResponse
import com.ncccdms.todolistbagian3.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val authRepository: AuthRepository
) : ViewModel() {

    var ongoingExpanded by mutableStateOf(true)
        private set
    var deadlineExpanded by mutableStateOf(true)
        private set
    var finishedExpanded by mutableStateOf(true)
        private set

    var ongoingTasks by mutableStateOf<List<Task>>(emptyList())
        private set
    var deadlineTasks by mutableStateOf<List<Task>>(emptyList())
        private set
    var finishedTasks by mutableStateOf<List<Task>>(emptyList())
        private set

    private val _tasksResponse = MutableStateFlow<TasksResponse>(Loading)
    val tasksResponse: StateFlow<TasksResponse> = _tasksResponse

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            val userId = authRepository.currentUser?.uid
            if (userId != null) {
                taskUseCases.getTasks(userId).collect { response ->
                    _tasksResponse.value = response

                    if (response is Success) {
                        val tasks = response.data
                        ongoingTasks = tasks.filter { it.taskStatus == TaskStatus.Ongoing }
                        deadlineTasks = tasks.filter { it.taskStatus == TaskStatus.Deadline }
                        finishedTasks = tasks.filter { it.taskStatus == TaskStatus.Finished }
                    }
                }
            } else {
                _tasksResponse.value = Failure(Exception("User not logged in"))
            }
        }
    }

    fun toggleOngoingExpanded() {
        ongoingExpanded = !ongoingExpanded
    }

    fun toggleDeadlineExpanded() {
        deadlineExpanded = !deadlineExpanded
    }

    fun toggleFinishedExpanded() {
        finishedExpanded = !finishedExpanded
    }
}
