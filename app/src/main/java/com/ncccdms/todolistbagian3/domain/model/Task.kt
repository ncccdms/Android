package com.ncccdms.todolistbagian3.domain.model

import com.ncccdms.todolistbagian3.core.Utils.Companion.parseDate
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class User(
    val id:String,
    val username:String
)

data class Task(
    val id: String,
    val title: String,
    val deadline: String = "no deadline listed",
    val statusDesc: String,
    val isFinished: Boolean,
    val creator: String,
    val createAt: String,
    val pic: String
) {
    val taskStatus: TaskStatus
        get() = when {
            isFinished -> TaskStatus.Finished
            isApproachingDeadline() -> TaskStatus.Deadline
            else -> TaskStatus.Ongoing
        }

    private fun isApproachingDeadline(): Boolean {
        val deadlineDate = parseDate(deadline) ?: return false
        val currentDate = LocalDate.now()
        val daysDifference = ChronoUnit.DAYS.between(currentDate, deadlineDate)
        return daysDifference in 0..10 || daysDifference < 0
    }
}

enum class TaskStatus {
    Finished,
    Ongoing,
    Deadline
}

