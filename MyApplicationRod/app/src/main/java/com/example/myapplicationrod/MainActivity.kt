package com.example.myapplication

import android.graphics.Color.parseColor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RGBColorPicker()
        }
    }
}

@Composable
fun RGBColorPicker() {
    val context = LocalContext.current

    var red by remember { mutableStateOf("") }
    var green by remember { mutableStateOf("") }
    var blue by remember { mutableStateOf("") }
    var colorHex by remember { mutableStateOf("#FFFFFF") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create an RGB Color", fontSize = 20.sp)
        Text(
            "Add two hexadecimal characters between 0-9, A-F or a-f",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = red,
            onValueChange = {
                if (it.length <= 2 && it.matches(Regex("[0-9a-fA-F]*"))) red = it.uppercase()
            },
            label = { Text("Red Channel") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                capitalization = KeyboardCapitalization.Characters
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = green,
            onValueChange = {
                if (it.length <= 2 && it.matches(Regex("[0-9a-fA-F]*"))) green = it.uppercase()
            },
            label = { Text("Green Channel") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                capitalization = KeyboardCapitalization.Characters
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = blue,
            onValueChange = {
                if (it.length <= 2 && it.matches(Regex("[0-9a-fA-F]*"))) blue = it.uppercase()
            },
            label = { Text("Blue Channel") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                capitalization = KeyboardCapitalization.Characters
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (red.length == 2 && green.length == 2 && blue.length == 2) {
                    colorHex = "#${red}${green}${blue}"
                } else {
                    Toast.makeText(context, "Enter valid 2-digit hex for R, G, B", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B))
        ) {
            Text("CREATE RGB COLOR", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color(parseColor(colorHex))),
            contentAlignment = Alignment.Center
        ) {
            Text("Created color display panel", color = Color.White)
        }
    }
}
