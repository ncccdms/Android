package com.ncccdms.todolistbagian3.data

import com.google.firebase.firestore.FirebaseFirestore
import com.ncccdms.todolistbagian3.domain.model.Response.Failure
import com.ncccdms.todolistbagian3.domain.model.Response.Success
import com.ncccdms.todolistbagian3.domain.model.Task
import com.ncccdms.todolistbagian3.domain.repository.AddTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.DeleteTaskResponse
import com.ncccdms.todolistbagian3.domain.repository.TasksRepository
import com.ncccdms.todolistbagian3.domain.repository.UpdateTaskResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): TasksRepository {
    override fun getTasksFromFirestore(userId: String) = callbackFlow {
        val tasksRef = firestore.collection("users").document(userId).collection("tasks")
        val snapshotListener = tasksRef.orderBy("createAt").addSnapshotListener { snapshot, e ->
            val tasksResponse = if (snapshot != null) {
                val tasks = snapshot.toObjects(Task::class.java)
                Success(tasks)
            } else {
                Failure(e ?: Exception("Unknown error occurred"))
            }
            trySend(tasksResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addTaskToFirestore(userId: String, task: Task): AddTaskResponse {
        return try {
            val tasksRef = firestore.collection("users").document(userId).collection("tasks")

            // Generate current date in "d MMMM yyyy" format
            val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            val currentDate = dateFormat.format(Date())

            // Create a new task with current date as createAt
            val taskWithDate = task.copy(createAt = currentDate)

            // Add the task to Firestore
            val documentRef = tasksRef.add(taskWithDate).await()

            // Optionally update the task with ID if needed
             val updatedTask = taskWithDate.copy(id = documentRef.id)
             documentRef.set(updatedTask).await()

            // Return Success with true if task was added successfully
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteTaskFromFirestore(userId: String, taskId: String): DeleteTaskResponse {
        return try {
            val taskRef = firestore.collection("users").document(userId).collection("tasks").document(taskId)
            taskRef.delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun updateTaskInFirestore(userId: String, task: Task): UpdateTaskResponse {
        return try {
            val taskRef = firestore.collection("users").document(userId).collection("tasks").document(task.id)
            taskRef.set(task).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
