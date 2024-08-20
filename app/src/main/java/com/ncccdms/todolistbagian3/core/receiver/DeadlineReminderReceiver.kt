package com.ncccdms.todolistbagian3.core.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.ncccdms.todolistbagian3.R
import com.ncccdms.todolistbagian3.core.Constant.CHANNEL_ID
import com.ncccdms.todolistbagian3.ui.MainActivity

class DeadlineReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getIntExtra("taskId", -1)
        val taskTitle = intent.getStringExtra("taskTitle")
        val taskStatusDesc = intent.getStringExtra("taskStatusDesc")

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_white)
            .setContentTitle(taskTitle)
            .setContentText(taskStatusDesc)
            .setStyle(NotificationCompat.BigTextStyle().bigText(taskStatusDesc))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 1000))
            .build()

        notificationManager.notify(taskId, notification)
    }
}
