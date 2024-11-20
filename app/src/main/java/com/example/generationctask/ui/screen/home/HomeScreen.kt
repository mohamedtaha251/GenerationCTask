package com.example.generationctask.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.generationctask.ui.viewmodel.CarViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: CarViewModel) {
    var price by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }


    viewModel.loadCars()
    // Use an initial empty list if cars is empty initially
    val cars by viewModel.cars.collectAsState(initial = emptyList())

    Column(Modifier.padding(16.dp)) {
        // Price TextField with placeholder for better visibility
        BasicTextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (price.isEmpty()) {
                        Text("Enter price", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        // Color TextField with placeholder
        BasicTextField(
            value = color,
            onValueChange = { color = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (color.isEmpty()) {
                        Text("Enter color", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        // Check if the cars list is empty
        if (cars.isEmpty()) {
            Text(text = "No cars available")
        } else {
            // Display the cars
            cars.forEach { car ->
                Row(Modifier.clickable { navController.navigate("details/${car.plateNumber}") }) {
                    Text(text = car.plateNumber)
                }
            }
        }
    }
}
