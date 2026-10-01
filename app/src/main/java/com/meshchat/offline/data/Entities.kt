package com.meshchat.offline.data
import androidx.room.Entity
@Entity data class Device(@androidx.room.PrimaryKey val id:String,val name:String,val address:String?,val lastSeen:Long)
@Entity data class Conversation(@androidx.room.PrimaryKey val id:String,val peerId:String,val title:String,val updatedAt:Long)
@Entity data class Message(@androidx.room.PrimaryKey val id:String,val conversationId:String,val senderId:String,val ciphertext:String,val timestamp:Long,val state:String)
@Entity data class Group(@androidx.room.PrimaryKey val id:String,val name:String,val isPublic:Boolean,val ownerId:String)
@Entity data class GroupMember(@androidx.room.PrimaryKey val id:String,val groupId:String,val deviceId:String,val admin:Boolean)
@Entity data class Status(@androidx.room.PrimaryKey val id:String,val ownerId:String,val text:String,val mediaUri:String?,val createdAt:Long,val expiresAt:Long)
@Entity data class MeshPacket(@androidx.room.PrimaryKey val packetId:String,val originId:String,val destinationId:String,val ttl:Int,val type:String,val encryptedPayload:String,val timestamp:Long)
@Entity data class RelayStatistics(@androidx.room.PrimaryKey val id:Int=1,val forwarded:Long=0,val stored:Long=0,val peers:Int=0)
@Entity data class CallHistory(@androidx.room.PrimaryKey val id:String,val peerId:String,val type:String,val startedAt:Long,val durationMs:Long)
