package com.meshchat.offline.relay
import android.content.*
import androidx.core.content.ContextCompat
class BootReceiver:BroadcastReceiver(){override fun onReceive(context:Context,intent:Intent){if(intent.action==Intent.ACTION_BOOT_COMPLETED&&context.getSharedPreferences("mesh",0).getBoolean("relay_enabled",false))ContextCompat.startForegroundService(context,Intent(context,RelayService::class.java))}}
