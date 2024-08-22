package com.ncccdms.todolistbagian3.domain.use_case

import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.domain.repository.AddTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.TasksRepository

class AddTask(
    private val repo: TasksRepository
) {
    suspend operator fun invoke(
        userId: String,
        task: Task
    ): AddTaskResponse = repo.addTaskToFirestore(userId, task)
}
