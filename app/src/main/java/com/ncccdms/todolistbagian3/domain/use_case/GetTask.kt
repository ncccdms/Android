package com.ncccdms.todolistbagian3.domain.use_case

import com.ncccdms.todolistbagian3.domain.repository.TasksRepository
import com.ncccdms.todolistbagian3.domain.repository.TasksResponse
import kotlinx.coroutines.flow.Flow

class GetTask(
    private val repo: TasksRepository
) {
    operator fun invoke(
        userId: String
    ): Flow<TasksResponse> = repo.getTasksFromFirestore(userId)
}