package com.meshchat.offline.mesh
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
class BluetoothMeshTransport(context:Context){private val adapter:BluetoothAdapter?=context.getSystemService(BluetoothManager::class.java)?.adapter;@SuppressLint("MissingPermission")fun isAvailable()=adapter?.isEnabled==true;@SuppressLint("MissingPermission")fun bondedPeers():List<BluetoothDevice>=adapter?.bondedDevices?.toList().orEmpty()}
