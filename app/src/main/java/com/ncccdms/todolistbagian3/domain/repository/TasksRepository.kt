package com.ncccdms.todolistbagian3.domain.repository

import com.ncccdms.todolistbagian3.domain.model.Response
import com.ncccdms.todolistbagian3.domain.model.Task
import kotlinx.coroutines.flow.Flow

typealias Tasks = List<Task>
typealias TasksResponse = Response<Tasks>
typealias AddTaskResponse = Response<Boolean>
typealias DeleteTaskResponse = Response<Boolean>
typealias UpdateTaskResponse = Response<Boolean>

interface TasksRepository {
    fun getTasksFromFirestore(userId: String): Flow<TasksResponse>

    suspend fun addTaskToFirestore(userId: String, task: Task): AddTaskResponse

    suspend fun deleteTaskFromFirestore(userId: String, taskId: String): DeleteTaskResponse

    suspend fun updateTaskInFirestore(userId: String, task: Task): UpdateTaskResponse
}