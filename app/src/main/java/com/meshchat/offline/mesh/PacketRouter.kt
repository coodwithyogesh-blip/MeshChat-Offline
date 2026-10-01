package com.meshchat.offline.mesh
import com.meshchat.offline.data.MeshPacketDao
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
class PacketRouter(private val dao:MeshPacketDao){private val seen=HashSet<String>();private val mutex=Mutex();suspend fun accept(packet:PacketEnvelope)=mutex.withLock{if(packet.ttl<=0||packet.payload.length>48000||!seen.add(packet.packetId))false else{dao.insert(packet.toEntity());true}};suspend fun nextHop(packet:PacketEnvelope)=if(packet.ttl<=1)null else packet.copy(ttl=packet.ttl-1)}
