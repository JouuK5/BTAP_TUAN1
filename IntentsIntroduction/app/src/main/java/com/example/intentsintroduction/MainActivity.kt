package com.example.intentsintroduction

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intentsintroduction.MainActivity.Companion.ADDRESS_KEY
import com.example.intentsintroduction.MainActivity.Companion.FULL_NAME_KEY
import com.example.intentsintroduction.ui.theme.IntentsIntroductionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsIntroductionTheme {
                MainScreen()
            }
        }
    }
    companion object {
        const val FULL_NAME_KEY = "FULL_NAME_KEY"
        const val ADDRESS_KEY = "ADDRESS_KEY"
    }
}


@Composable
private fun MainScreen(){
    var fullName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("")}
    val context = LocalContext.current

    val welcomeIntent =
        Intent(context, WelcomeActivity::class.java)
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.full_name_label),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.address_label),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = ({
                    if(fullName.isNotEmpty() && address.isNotEmpty()){
                        welcomeIntent.putExtra(FULL_NAME_KEY, fullName)
                        welcomeIntent.putExtra(ADDRESS_KEY, address)
                        context.startActivity(welcomeIntent)
                    }else if(fullName.isEmpty()){
                        Toast.makeText(
                            context, context.getString(R.string.full_name_label),
                            Toast.LENGTH_LONG
                        ).show()
                    }else{
                        Toast.makeText(
                            context, context.getString(R.string.address_label),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = stringResource(id = R.string.submit_btn)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview(){
    MainScreen()
}