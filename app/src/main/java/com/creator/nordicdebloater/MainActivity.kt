package com.creator.nordicdebloater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.creator.nordicdebloater.ui.theme.*
import com.creator.nordicdebloater.logic.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
        setContent {
            NordicTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isShizukuActive = ShizukuManager.isAvailable()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(containerColor = PolarNight) {
                Text("NORDIC", color = FrostBlue, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(24.dp))
                NavigationDrawerItem(label = { Text("Credits") }, selected = false, onClick = {})
                NavigationDrawerItem(label = { Text("Privacy Policy") }, selected = false, onClick = {})
                NavigationDrawerItem(label = { Text("Terms of Use") }, selected = false, onClick = {})
                Spacer(modifier = Modifier.weight(1f))
                
                // Outlined Support Button
                OutlinedButton(
                    onClick = { /* Link to your socials */ },
                    border = BorderStroke(1.dp, FrostBlue),
                    modifier = Modifier.padding(20.dp).fillMaxWidth()
                ) {
                    Text("Support the Creator", color = FrostBlue)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("DEBLOATER", color = SnowStorm) },
                    navigationIcon = {
                        // Double Dot Icon
                        Column(
                            modifier = Modifier.padding(16.dp).clickable { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } },
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(FrostBlue))
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(FrostBlue))
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                if (!isShizukuActive) {
                    Text("WARNING: Shizuku is not responding!", color = AuroraRed, modifier = Modifier.align(Alignment.Center))
                } else {
                    AppList()
                }
            }
        }
    }
}

@Composable
fun AppList() {
    val apps = Scanner.targetBloat
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(apps) { app ->
            // Ease-in Ease-out Animation for items
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { visible = true }
            
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(500, easing = LinearOutSlowInEasing)) + 
                        expandVertically(animationSpec = tween(500))
            ) {
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4252))) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(app.name, color = SnowStorm)
                            Text(app.packageName, color = FrostBlue, style = MaterialTheme.typography.bodySmall)
                        }
                        Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = AuroraRed)) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
