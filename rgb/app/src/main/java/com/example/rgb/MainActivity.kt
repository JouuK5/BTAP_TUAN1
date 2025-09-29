package com.example.rgb

import android.R
import android.R.attr.label
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.rgb.ui.theme.RgbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RgbTheme {
                val context = LocalContext.current
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            var red by remember {mutableStateOf("")}
                            var green by remember {mutableStateOf("")}
                            var blue by remember {mutableStateOf("")}
                            var colorToDisplay by remember { mutableStateOf(Color.Blue) }

                            Text("Enter three color to see the result!!")

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = red,
                                onValueChange = { red = it },
                                label = {
                                    Text("Red")
                                }
                            )

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = green,
                                onValueChange = { green = it },
                                label = {
                                    Text("Green")
                                }
                            )

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = blue,
                                onValueChange = { blue = it },
                                label = {
                                    Text("Blue")
                                }
                            )

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    if(isValid(red) && isValid(green) && isValid(blue)){
                                        var colorString = "#$red$green$blue"
                                        colorToDisplay = Color(colorString.toColorInt())
                                    }
                                    if(red.isEmpty() || green.isEmpty() || blue.isEmpty()){
                                        val toast = Toast.makeText(
                                            context,
                                            "Enter full!",
                                            Toast.LENGTH_LONG
                                        )
                                        toast.show()
                                    }
                                }
                            ){
                                Text(text = "Create")
                            }
                        Text(
                            modifier = Modifier.background(colorToDisplay).padding(24.dp),
                            text = "Result"
                        )
                    }
                }
            }
        }
    }
}

fun isValid(input: String): Boolean{
    return input.filter {
        it in '0'..'9' ||
                it in 'A'..'F' ||
                it in 'a'..'f'
    }.length == 2
}

