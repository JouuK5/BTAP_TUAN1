package com.example.test

import android.os.Bundle
import android.view.Gravity
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                var firstName by rememberSaveable { mutableStateOf("") }
                var lastName by rememberSaveable { mutableStateOf("") }
                var fullName by rememberSaveable { mutableStateOf("") }

                var welcomeMessage = stringResource(id = R.string.welcome_to_the_app)
                var enterNameErrorMessage = stringResource(id = R.string.please_enter_a_name)

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                        Text("hello hell")

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = {
                                Text(
                                    stringResource(id = R.string.first_name)
                                )
                            },
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = {
                                Text(
                                    stringResource(id = R.string.last_name)
                                )
                            },
                        )
                        val context = LocalContext.current
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if(firstName.isNotBlank() && lastName.isNotBlank())
                                    fullName = "$firstName $lastName"
                                else{
                                    fullName = ""
                                    val toast = Toast.makeText(
                                        context,
                                        enterNameErrorMessage,
                                        Toast.LENGTH_LONG
                                    )
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0)
                                    toast.show()
                                }
                            }
                        ) {
                            Text("Enter")
                        }
                        if(fullName.isNotEmpty()){
                            Text(text = "$welcomeMessage $fullName")
                        }
                    }
                }
            }
        }
    }
}



