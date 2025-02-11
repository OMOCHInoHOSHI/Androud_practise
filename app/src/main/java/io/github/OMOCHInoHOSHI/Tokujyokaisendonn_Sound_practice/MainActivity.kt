package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_Sound_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_Sound_practice.ui.theme.Androud_practiseTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundSNSTheme {
                SoundSNSApp()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello !  $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoundSNSTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundSNSApp() {
    var selectedTab by remember { mutableStateOf(0) }

    SoundSNSTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("SoundSNS", fontWeight = FontWeight.Bold) },
                    actions = {
                        // プロフィールアイコン
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Person, contentDescription = "プロフィール")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, "ホーム") },
                        label = { Text("ホーム") },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Mic, "録音") },
                        label = { Text("録音") },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Notifications, "通知") },
                        label = { Text("通知") },
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 }
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                // タブ
                TabRow(selectedTabIndex = 0) {
                    Tab(
                        selected = true,
                        onClick = { },
                        text = { Text("Hot") }
                    )
                    Tab(
                        selected = false,
                        onClick = { },
                        text = { Text("新着順") }
                    )
                    Tab(
                        selected = false,
                        onClick = { },
                        text = { Text("フォロー中") }
                    )
                }

                // 検索バー
                TextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    placeholder = { Text("検索") },
                    leadingIcon = { Icon(Icons.Default.Search, "検索") }
                )

                // AUTO再生ボタン
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("AUTO", fontWeight = FontWeight.Bold)
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.PlayArrow, "再生")
                    }
                }

                // 投稿リスト
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(3) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // 音声アイコン
                                    Surface(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(CircleShape),
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    ) {
                                        Icon(
                                            Icons.Default.Mic,
                                            contentDescription = "音声",
                                            modifier = Modifier.padding(12.dp)
                                        )
                                    }

                                    Column {
                                        Text(
                                            "user name",
                                            fontWeight = FontWeight.Medium
                                        )
                                        Text(
                                            "#",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                // アクションボタン
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Default.Favorite, "いいね")
                                    }
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Default.BookmarkBorder, "ブックマーク")
                                    }
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Default.Share, "シェア")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private val LightColors = lightColorScheme(
    primary = Color(0xFF6750A4),
    secondary = Color(0xFF625B71),
    tertiary = Color(0xFF7D5260),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    secondary = Color(0xFFCCC2DC),
    tertiary = Color(0xFFEFB8C8),
)

@Composable
fun SoundSNSTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}