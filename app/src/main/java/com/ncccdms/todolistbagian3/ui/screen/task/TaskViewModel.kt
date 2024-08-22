package com.ncccdms.todolistbagian3.ui.screen.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncccdms.todolistbagian3.domain.model.Response.Failure
import com.ncccdms.todolistbagian3.domain.model.Response.Loading
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.domain.repository.AddTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import com.ncccdms.todolistbagian3.domain.repository.DeleteTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.TasksResponse
import com.ncccdms.todolistbagian3.domain.repository.UpdateTaskResponse
import com.ncccdms.todolistbagian3.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val useCases: TaskUseCases,
    private val authRepository: AuthRepository
) : ViewModel() {

    var taskResponse by mutableStateOf<TasksResponse>(Loading)
        private set
    var addTaskResponse by mutableStateOf<AddTaskResponse>(Loading) // Initialize with Loading state
        private set
    var deleteTaskResponse by mutableStateOf<DeleteTaskResponse>(Success(false))
        private set
    var updateTaskResponse by mutableStateOf<UpdateTaskResponse>(Success(false))
        private set

    init {
        getTasks()
    }

    private fun getTasks() = viewModelScope.launch {
        val userId = authRepository.currentUser?.uid // Get the current user ID
        if (userId != null) {
            useCases.getTasks(userId).collect { response ->
                taskResponse = response
            }
        } else {
            taskResponse = Failure(Exception("User ID is null"))
        }
    }

    fun addTask(
        title: String,
        deadline: String,
        statusDesc: String,
        isFinished: Boolean,
        pic: String
    ) = viewModelScope.launch {
        val userId = authRepository.currentUser?.uid // Get the current user ID
        if (userId != null) {
            addTaskResponse = Loading // Set loading state

            try {
                // Retrieve the username for the current user
                when (val usernameResponse = authRepository.getUsername(userId)) {
                    is Success -> {
                        val username = usernameResponse.data

                        val task = Task(
                            id = "",
                            title = title,
                            deadline = deadline,
                            statusDesc = statusDesc,
                            isFinished = isFinished,
                            creator = username,
                            createAt = "",
                            pic = pic
                        )

                        // Add task and get the result
                        when (val response = useCases.addTask(userId, task)) {
                            is Success -> {
                                addTaskResponse = Success(true)
                            }
                            is Failure -> {
                                addTaskResponse = Failure(response.e)
                            }

                            Loading -> Loading
                        }
                    }
                    is Failure -> {
                        addTaskResponse = Failure(usernameResponse.e)
                    }
                    is Loading -> {
                        // Handle loading state if needed
                        addTaskResponse = Loading
                    }
                }
            } catch (e: Exception) {
                addTaskResponse = Failure(e)
            }
        } else {
            addTaskResponse = Failure(Exception("User ID is null"))
        }
    }


    fun deleteTask(taskId: String) = viewModelScope.launch {
        val userId = authRepository.currentUser?.uid // Get the current user ID
        if (userId != null) {
            deleteTaskResponse = Loading // Set loading state
            deleteTaskResponse = useCases.deleteTask(userId, taskId)
        } else {
            deleteTaskResponse = Failure(Exception("User ID is null"))
        }
    }

    fun updateTask(
        taskId: String,
        title: String,
        deadline: String,
        statusDesc: String,
        isFinished: Boolean,
        pic: String
    ) = viewModelScope.launch {
        val userId = authRepository.currentUser?.uid // Get the current user ID
        if (userId != null) {
            updateTaskResponse = Loading // Set loading state
            updateTaskResponse = useCases.updateTask(
                userId = userId,
                task = Task(
                    id = taskId,
                    title = title,
                    deadline = deadline,
                    statusDesc = statusDesc,
                    isFinished = isFinished,
                    creator = userId, // Assuming the creator is the current user
                    createAt = "", // This could be updated as needed
                    pic = pic
                )
            )
        } else {
            updateTaskResponse = Failure(Exception("User ID is null"))
        }
    }
}

