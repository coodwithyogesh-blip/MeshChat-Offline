package com.meshchat.offline.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao interface MeshPacketDao{@Insert(onConflict=OnConflictStrategy.IGNORE)suspend fun insert(packet:MeshPacket);@Query("SELECT * FROM MeshPacket WHERE packetId=:id")suspend fun find(id:String):MeshPacket?;@Query("DELETE FROM MeshPacket WHERE timestamp<:cutoff")suspend fun deleteExpired(cutoff:Long);@Query("SELECT * FROM MeshPacket ORDER BY timestamp LIMIT 100")fun observe():Flow<List<MeshPacket>>}
@Dao interface MessageDao{@Insert(onConflict=OnConflictStrategy.REPLACE)suspend fun insert(message:Message);@Query("SELECT * FROM Message WHERE conversationId=:id ORDER BY timestamp")fun observe(id:String):Flow<List<Message>>}
@Dao interface DeviceDao{@Query("SELECT * FROM Device ORDER BY lastSeen DESC")fun observe():Flow<List<Device>>}
@Dao interface StatusDao{@Insert(onConflict=OnConflictStrategy.REPLACE)suspend fun insert(status:Status);@Query("SELECT * FROM Status WHERE expiresAt>:now ORDER BY createdAt DESC")fun observe(now:Long):Flow<List<Status>>}
