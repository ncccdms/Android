package com.ncccdms.todolistbagian3.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.ncccdms.todolistbagian3.core.Constant.TAG
import com.ncccdms.todolistbagian3.data.dummy.Task
import com.ncccdms.todolistbagian3.data.dummy.TaskStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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

        // Convert tasks to a sortable format and sort
        fun sortTasksByDeadline(tasks: List<Task>): List<Task> {
            return tasks
                .filter { it.taskStatus == TaskStatus.Deadline } // Only consider tasks with Deadline status
                .sortedBy { parseDate(it.deadline) } // Sort tasks by parsed date
        }
    }
}