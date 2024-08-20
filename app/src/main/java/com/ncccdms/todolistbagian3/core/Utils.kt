package com.ncccdms.todolistbagian3.core

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.ncccdms.todolistbagian3.core.Constant.CHANNEL_ID
import com.ncccdms.todolistbagian3.core.Constant.TAG
import com.ncccdms.todolistbagian3.core.receiver.DeadlineReminderReceiver
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()

        fun setSplashScreenShown(context: Context) {
            val sharedPref = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("isSplashScreenShown", true)
                apply()
            }
        }

        fun getIsSplashScreenShown(context: Context): Boolean {
            val sharedPref = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
            return sharedPref.getBoolean("isSplashScreenShown", false)
        }

        fun validateEmail(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            return email.matches(Regex(emailPattern))
        }

        fun validatePassword(password: String): Boolean {
            val passwordPattern = "^(?=.*[A-Z])(?=.*[!@#\$&*]).{8,}$"
            return password.matches(Regex(passwordPattern))
        }

        fun parseDate(dateString: String): LocalDate? {
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
            return try {
                LocalDate.parse(dateString, formatter)
            } catch (e: DateTimeParseException) {
                null
            }
        }

        fun convertMillisToDate(millis: Long): String {
            val formatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            return formatter.format(Date(millis))
        }

        // Convert tasks to a sortable format and sort
        fun sortTasksByDeadline(tasks: List<Task>): List<Task> {
            return tasks
                .filter { it.taskStatus == TaskStatus.Deadline } // Only consider tasks with Deadline status
                .sortedBy { parseDate(it.deadline) } // Sort tasks by parsed date
        }

        // Function to calculate and update the remaining days
        fun calculateRemainingDays(deadline: String): Long {
            val deadlineDate = parseDate(deadline)
            val currentDate = LocalDate.now()

            return if (deadlineDate != null) {
                ChronoUnit.DAYS.between(currentDate, deadlineDate)
            } else {
                0 // Default to 0 if the deadline is invalid
            }
        }
    }
}