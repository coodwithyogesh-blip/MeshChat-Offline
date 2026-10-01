package com.meshchat.offline.relay
import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
class RelayService:Service(){override fun onCreate(){super.onCreate();getSystemService(NotificationManager::class.java).createNotificationChannel(NotificationChannel("relay","Mesh Relay",NotificationManager.IMPORTANCE_LOW));startForeground(1001,NotificationCompat.Builder(this,"relay").setSmallIcon(android.R.drawable.stat_sys_data_bluetooth).setContentTitle("Mesh Relay Active").setContentText("This phone is forwarding encrypted mesh traffic.").setOngoing(true).build())};override fun onStartCommand(intent:Intent?,flags:Int,startId:Int)=START_STICKY;override fun onBind(intent:Intent?):IBinder?=null}
