package com.ncccdms.todolistbagian3.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.ncccdms.todolistbagian3.core.Constant.TAG

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
    }
}