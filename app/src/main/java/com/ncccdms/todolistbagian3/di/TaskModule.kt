package com.ncccdms.todolistbagian3.di

import com.google.firebase.firestore.FirebaseFirestore
import com.ncccdms.todolistbagian3.data.TasksRepositoryImpl
import com.ncccdms.todolistbagian3.domain.repository.TasksRepository
import com.ncccdms.todolistbagian3.domain.use_case.AddTask
import com.ncccdms.todolistbagian3.domain.use_case.DeleteTask
import com.ncccdms.todolistbagian3.domain.use_case.GetTask
import com.ncccdms.todolistbagian3.domain.use_case.TaskUseCases
import com.ncccdms.todolistbagian3.domain.use_case.UpdateTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    fun provideTasksRepository(
        firestore: FirebaseFirestore
    ): TasksRepository = TasksRepositoryImpl(firestore)

    @Provides
    fun provideUseCases(
        repo: TasksRepository
    ): TaskUseCases = TaskUseCases(
        getTasks = GetTask(repo),
        addTask = AddTask(repo),
        deleteTask = DeleteTask(repo),
        updateTask = UpdateTask(repo)
    )
}
