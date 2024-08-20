package com.ncccdms.todolistbagian3.core.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ncccdms.todolistbagian3.core.receiver.DeadlineReminderReceiver
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import java.util.Calendar

class AlarmScheduler(private val context: Context) {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @SuppressLint("ScheduleExactAlarm")
    fun scheduleTaskDeadlineNotification(taskId: Int, taskTitle: String, taskStatusDesc: String, triggerTime: Long) {
        val intent = Intent(context, DeadlineReminderReceiver::class.java).apply {
            putExtra("taskId", taskId)
            putExtra("taskTitle", taskTitle)
            putExtra("taskStatusDesc", taskStatusDesc)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId, // Use taskId to ensure uniqueness
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Schedule the alarm to trigger at 8 AM
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }

    private fun getTriggerTime(): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (timeInMillis < System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1) // Schedule for the next day if time is past 8 AM today
            }
        }
        return calendar.timeInMillis
    }

    fun scheduleDailyTaskDeadlineNotification(sortedTasks: List<Task>) {
        sortedTasks.forEach { task ->
            if (task.taskStatus == TaskStatus.Deadline) {
                scheduleTaskDeadlineNotification(
                    task.id,
                    task.title,
                    task.statusDesc,
                    getTriggerTime()
                )
            }
        }
    }
}