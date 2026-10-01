package com.meshchat.offline
import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.meshchat.offline.relay.RelayService

class MainActivity:ComponentActivity(){
 private val permissions=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){}
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{MeshChatApp()}}
 @OptIn(ExperimentalMaterial3Api::class)
 @Composable fun MeshChatApp(){var tab by remember{mutableIntStateOf(0)};val tabs=listOf("Chats","Calls","Status","Groups","Relay","Settings");MaterialTheme{Scaffold(topBar={TopAppBar(title={Text("MeshChat Offline")})},bottomBar={NavigationBar{tabs.forEachIndexed{i,t->NavigationBarItem(selected=tab==i,onClick={tab=i},icon={Text(t.take(1))},label={Text(t)})}}}){pad->Box(Modifier.padding(pad).fillMaxSize()){when(tab){0->Screen("Chats","No conversations yet. Discover a nearby peer to start.");1->Screen("Calls","Voice/video calling architecture is transport-aware; live multi-hop video is not assumed.");2->StatusScreen();3->Screen("Groups","Create private or public groups when peers are available.");4->RelayScreen();else->SettingsScreen()}}}}}
 private fun requestPermissions(){val p=mutableListOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA);if(Build.VERSION.SDK_INT>=31)p+=listOf(Manifest.permission.BLUETOOTH_SCAN,Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_ADVERTISE);permissions.launch(p.toTypedArray())}
 @Composable fun Screen(title:String,body:String){Column(Modifier.padding(20.dp)){Text(title,style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(12.dp));Text(body);Spacer(Modifier.height(24.dp));Button(onClick={requestPermissions()}){Text("Grant device permissions")}}}
 @Composable fun StatusScreen(){var text by remember{mutableStateOf("")};Column(Modifier.padding(20.dp)){Text("Status",style=MaterialTheme.typography.headlineMedium);OutlinedTextField(text,{text=it},label={Text("Status text")},modifier=Modifier.fillMaxWidth());Spacer(Modifier.height(12.dp));Button(onClick={},enabled=text.isNotBlank()){Text("Post status")};Spacer(Modifier.height(24.dp));Text("Statuses expire automatically when their expiration time is reached.")}}
 @Composable fun RelayScreen(){val prefs=getSharedPreferences("mesh",0);var enabled by remember{mutableStateOf(prefs.getBoolean("relay_enabled",false))};Column(Modifier.padding(20.dp)){Text("Relay",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(12.dp));Text(if(enabled)"Relay is active. Encrypted traffic may be forwarded through this phone." else "Relay is disabled.");Spacer(Modifier.height(12.dp));Button(onClick={enabled=!enabled;prefs.edit().putBoolean("relay_enabled",enabled).apply();val i=Intent(this@MainActivity,RelayService::class.java);if(enabled)ContextCompat.startForegroundService(this@MainActivity,i)else stopService(i)}){Text(if(enabled)"Disable Relay" else "Enable Relay")};Spacer(Modifier.height(12.dp));Text("Android may restrict background operation.")}}
 @Composable fun SettingsScreen(){Column(Modifier.padding(20.dp)){Text("Settings",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(12.dp));Button(onClick={requestPermissions()}){Text("Manage permissions")};Spacer(Modifier.height(16.dp));Text("MeshChat Offline uses encrypted packets and does not assume Bluetooth pairing is end-to-end encryption.")}}
}
