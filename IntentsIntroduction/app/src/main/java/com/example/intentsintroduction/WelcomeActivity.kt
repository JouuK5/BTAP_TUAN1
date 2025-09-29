package com.example.intentsintroduction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intentsintroduction.MainActivity.Companion.ADDRESS_KEY
import com.example.intentsintroduction.MainActivity.Companion.FULL_NAME_KEY
import com.example.intentsintroduction.ui.theme.IntentsIntroductionTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsIntroductionTheme {
                WelcomeScreen(intent)
            }
        }
    }
}

@Composable
private fun WelcomeScreen(intent: Intent) {
    val context = LocalContext.current
    val back = (context as? ComponentActivity)
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val fullName = intent.getStringExtra(FULL_NAME_KEY) ?: ""
                val welcomeText = stringResource(id = R.string.welcome_text, fullName)
                val address = intent.getStringExtra(ADDRESS_KEY) ?: ""

                Image(
                    painter = painterResource(id = R.drawable.profile), // ảnh bạn thêm vào drawable
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(200.dp)        // kích thước avatar
                        .clip(CircleShape)   // bo tròn ảnh
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = fullName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = address,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(3.dp)
                )
            }

            Button(
                onClick = { back?.finish() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
            ) {
                Text("Back")
            }
        }
    }
}
