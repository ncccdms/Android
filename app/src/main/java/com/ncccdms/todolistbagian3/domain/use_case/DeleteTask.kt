package com.ncccdms.todolistbagian3.domain.use_case

import com.ncccdms.todolistbagian3.domain.repository.TasksRepository

class DeleteTask(
    private val repo: TasksRepository
) {
    suspend operator fun invoke(
        userId: String,
        taskId: String
    ) = repo.deleteTaskFromFirestore(userId, taskId)
}
