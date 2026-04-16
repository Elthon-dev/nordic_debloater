package com.creator.nordicdebloater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creator.nordicdebloater.ui.theme.*
import com.creator.nordicdebloater.logic.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
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
                title = { Text("Nordic Debloater", color = SnowStorm) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PolarNight)
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (!isShizukuActive) {
                Text(
                    text = "Shizuku is not running or permission denied.",
                    color = AuroraRed,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                AppList()
            }
        }
    }
}

@Composable
fun AppList() {
    val apps = Scanner.targetBloat
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(apps) { app ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4252))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(app.name, color = SnowStorm, style = MaterialTheme.typography.titleMedium)
                        Text(app.packageName, color = FrostBlue, style = MaterialTheme.typography.bodySmall)
                    }
                    Button(
                        onClick = { /* Uninstall Logic will go here */ },
                        colors = ButtonDefaults.buttonColors(containerColor = AuroraRed)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
