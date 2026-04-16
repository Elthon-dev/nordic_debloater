package com.creator.nordicdebloater

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.creator.nordicdebloater.ui.theme.*
import com.creator.nordicdebloater.logic.*
import rikka.shizuku.Shizuku

class MainActivity : ComponentActivity() {
    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
        
        // Request Shizuku permission on launch
        if (ShizukuManager.isAvailable() && Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(0)
        }

        setContent {
            NordicTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val isShizukuActive = remember { ShizukuManager.isAvailable() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nordic Debloater", fontWeight = FontWeight.Bold, color = FrostBlue) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PolarNight)
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (!isShizukuActive) {
                Card(
                    modifier = Modifier.align(Alignment.Center).padding(32.dp),
                    colors = CardDefaults.cardColors(containerColor = PolarNightLight)
                ) {
                    Text(
                        text = "Shizuku is disconnected.\nPlease start it via Termux or Wireless Debugging.",
                        color = AuroraRed,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                AppList()
            }
        }
    }
}

@Composable
fun AppList() {
    // We use a mutable state list so the UI updates when an app is deleted
    val apps = remember { mutableStateListOf(*Scanner.targetBloat.toTypedArray()) }
    var actionMessage by remember { mutableStateOf("Ready to purge.") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = actionMessage,
            color = AuroraGreen,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(apps) { app ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = PolarNightLight)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(app.name, color = SnowStorm, fontWeight = FontWeight.Bold)
                            Text(app.packageName, color = FrostBlue, style = MaterialTheme.typography.bodySmall)
                        }
                        Button(
                            onClick = { 
                                val success = ShizukuManager.uninstallApp(app.packageName)
                                if (success) {
                                    apps.remove(app)
                                    actionMessage = "Purged: ${app.name}"
                                } else {
                                    actionMessage = "Failed to purge: ${app.name}"
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = AuroraRed),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Purge")
                        }
                    }
                }
            }
        }
    }
}
