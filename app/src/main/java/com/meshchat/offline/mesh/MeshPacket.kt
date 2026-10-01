package com.meshchat.offline.mesh
import com.meshchat.offline.data.MeshPacket
import java.util.UUID
data class PacketEnvelope(val packetId:String,val originId:String,val destinationId:String,val ttl:Int,val type:String,val payload:String,val timestamp:Long){fun toEntity()=MeshPacket(packetId,originId,destinationId,ttl,type,payload,timestamp);companion object{fun create(origin:String,destination:String,type:String,payload:String,ttl:Int=8)=PacketEnvelope(UUID.randomUUID().toString(),origin,destination,ttl.coerceIn(0,16),type,payload,System.currentTimeMillis())}}
