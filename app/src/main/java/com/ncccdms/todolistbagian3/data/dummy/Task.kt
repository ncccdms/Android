package com.ncccdms.todolistbagian3.data.dummy

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
                deadline.isNotEmpty() && isDeadlineWithin1Day() -> TaskStatus.Deadline
                else -> TaskStatus.Ongoing
            }
        }

    private fun isDeadlineWithin1Day(): Boolean {
        // Replace this logic with the actual date comparison to check if the deadline is within 1 day
        return false
    }
}


enum class TaskStatus {
    Finished,
    Ongoing,
    Deadline
}

