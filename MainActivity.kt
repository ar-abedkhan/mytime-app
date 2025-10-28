package com.example.mytime

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSetReminder: Button = findViewById(R.id.btnSetReminder)
        btnSetReminder.setOnClickListener {
            // Example: schedule a reminder 1 minute from now (for testing)
            val cal = Calendar.getInstance()
            cal.add(Calendar.MINUTE, 1)
            scheduleReminder(cal.timeInMillis, "Time to study! (MyTime)")
        }
    }

    private fun scheduleReminder(timeInMillis: Long, message: String) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java)
        intent.putExtra("message", message)
        val pending = PendingIntent.getBroadcast(this, 1001, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pending)
    }
}
