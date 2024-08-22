package com.ncccdms.todolistbagian3.domain.use_case

data class TaskUseCases(
    val getTasks: GetTask,
    val addTask: AddTask,
    val deleteTask: DeleteTask,
    val updateTask: UpdateTask
)