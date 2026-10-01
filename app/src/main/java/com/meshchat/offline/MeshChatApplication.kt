package com.meshchat.offline
import android.app.Application
import com.meshchat.offline.data.AppDatabase
import com.meshchat.offline.security.EncryptionManager
import com.meshchat.offline.mesh.PacketRouter
class MeshChatApplication:Application(){val database by lazy{AppDatabase.create(this)};val encryption by lazy{EncryptionManager(this)};val router by lazy{PacketRouter(database.meshPacketDao())}}
