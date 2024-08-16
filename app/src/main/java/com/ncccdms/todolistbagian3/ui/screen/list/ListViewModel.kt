package com.ncccdms.todolistbagian3.ui.screen.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ncccdms.todolistbagian3.data.dummy.TaskData
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): ViewModel(){
    var ongoingExpanded by mutableStateOf(true)
        private set
    var deadlineExpanded by mutableStateOf(true)
        private set
    var finishedExpanded by mutableStateOf(true)
        private set

    val ongoingTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Ongoing }
    val deadlineTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Deadline }
    val finishedTasks = TaskData.dummytask.filter { it.taskStatus == TaskStatus.Finished }

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