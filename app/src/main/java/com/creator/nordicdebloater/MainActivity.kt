package com.creator.nordicdebloater
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creator.nordicdebloater.ui.theme.*
import com.creator.nordicdebloater.logic.*

class MainActivity : ComponentActivity() {
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        setContent { NordicTheme { MainScreen() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val apps = remember { mutableStateListOf("com.heytap.market", "com.coloros.themestore", "com.coloros.browser", "com.oppo.quicksearchbox") }
    Scaffold(topBar = { TopAppBar(title = { Text("Nordic Debloater", color = FrostBlue) }) }) { p ->
        LazyColumn(Modifier.padding(p).fillMaxSize().padding(16.dp)) {
            items(apps) { pkg ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(Modifier.padding(16.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Text(pkg.split(".").last().uppercase(), Modifier.weight(1f), color = SnowStorm)
                        Button(onClick = { if(ShizukuManager.uninstall(pkg)) apps.remove(pkg) }, colors = ButtonDefaults.buttonColors(containerColor = AuroraRed)) {
                            Text("PURGE")
                        }
                    }
                }
            }
        }
    }
}
