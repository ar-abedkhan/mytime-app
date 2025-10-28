package com.example.mytime

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("message") ?: "MyTime reminder"
        val builder = NotificationCompat.Builder(context, "mytime_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("MyTime")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            // Notification id should be unique for multiple notifications
            notify(System.currentTimeMillis().toInt() and 0xfffffff, builder.build())
        }
    }
}
