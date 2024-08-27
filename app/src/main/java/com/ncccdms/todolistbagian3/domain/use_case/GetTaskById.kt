package com.ncccdms.todolistbagian3.domain.use_case

import com.ncccdms.todolistbagian3.domain.repository.SingleTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.TasksRepository

class GetTaskById(
    private val repo: TasksRepository
) {
    suspend operator fun invoke(taskId: String): SingleTaskResponse = repo.getTaskById(taskId)
}
