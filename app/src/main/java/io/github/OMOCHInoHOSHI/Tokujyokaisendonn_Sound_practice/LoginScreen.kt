package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_Sound_practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(): Boolean {
    // ログイン結果
    val loginState by remember { mutableStateOf(false) }
    // メールアドレス
    var email: String? by remember { mutableStateOf(null) }
    // パスワード
    var password: String? by remember { mutableStateOf(null) }

    Scaffold(
        content =  { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // ここで innerPadding を適用
            )
             {
//                Image(
//                    painter = painterResource(id = R.drawable.risrisris),
//                    contentDescription = "背景画像",
//                    contentScale = ContentScale.FillHeight,
//                    modifier = Modifier.fillMaxSize()
//                )

                // メールアドレスとパスワードの入力UIS----------------------------------------------------------------------
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {

                    // メアドS------------------------------------------------------------
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "メールアドレスを入力",
                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = email ?: "",
                        onValueChange = { email = it },
                        label = { Text("メールアドレス") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    // メアドE------------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // パスワード入力S------------------------------------------------------
                    Text(
                        text = "パスワードは6文字以上",
                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = password ?: "",
                        onValueChange = { password = it },
                        label = { Text("パスワード") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    // パスワード入力E------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // ログインS-----------------------------------------------------------
                    Button(
                        onClick = { /* ログイン処理 */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ログイン", fontSize = 18.sp)
                    }
                    // ログインE-----------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // 新規登録S-----------------------------------------------------------
                    Button(
                        onClick = { /* 新規登録処理 */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("新規登録", fontSize = 18.sp)
                    }
                    // 新規登録E-----------------------------------------------------------

                }
            }
        }
        // メールアドレスとパスワードの入力UIS----------------------------------------------------------------------

    )

    return loginState
}