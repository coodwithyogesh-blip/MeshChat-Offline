package com.meshchat.offline.data
import android.content.Context
import androidx.room.*
@Database(entities=[Device::class,Conversation::class,Message::class,Group::class,GroupMember::class,Status::class,MeshPacket::class,RelayStatistics::class,CallHistory::class],version=1,exportSchema=false)
abstract class AppDatabase:RoomDatabase(){abstract fun meshPacketDao():MeshPacketDao;abstract fun messageDao():MessageDao;abstract fun deviceDao():DeviceDao;abstract fun statusDao():StatusDao;companion object{fun create(context:Context)=Room.databaseBuilder(context,AppDatabase::class.java,"meshchat.db").fallbackToDestructiveMigration().build()}}
