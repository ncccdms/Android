package com.ncccdms.todolistbagian3.data.dummy

import com.ncccdms.todolistbagian3.core.Utils.Companion.parseDate
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Task(
    val id: Int,
    val title: String,
    val deadline: String = "no deadline listed",
    val statusDesc: String,
    val isFinished: Boolean,
    val creator: String,
    val createAt: String,
    val pic: String
) {
    val taskStatus: TaskStatus
        get() {
            return when {
                isFinished -> TaskStatus.Finished
                isDeadline() || isPastDeadline()-> TaskStatus.Deadline
                else -> TaskStatus.Ongoing
            }
        }

    private fun isDeadline(): Boolean {
        val deadlineDate = parseDate(deadline) ?: return false
        val currentDate = LocalDate.now()
        val daysDifference = ChronoUnit.DAYS.between(currentDate, deadlineDate)
        return daysDifference in 0..10
    }

    private fun isPastDeadline(): Boolean {
        val deadlineDate = parseDate(deadline) ?: return false
        val currentDate = LocalDate.now()
        val daysDifference = ChronoUnit.DAYS.between(currentDate, deadlineDate)
        return daysDifference < 0
    }

}


enum class TaskStatus {
    Finished,
    Ongoing,
    Deadline
}

