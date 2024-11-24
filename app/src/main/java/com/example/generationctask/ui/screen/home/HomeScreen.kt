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
import androidx.compose.runtime.LaunchedEffect
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
    var model by remember { mutableStateOf("") }
    val cars by viewModel.cars.collectAsState(initial = emptyList())

    LaunchedEffect(model) {
        if (model.isNotEmpty()) {
            viewModel.searchCars(model)
        } else {
            viewModel.loadCars()
        }
    }

    Column(Modifier.padding(16.dp)) {
        BasicTextField(
            value = model,
            onValueChange = { model = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (model.isEmpty()) {
                        Text("Enter model", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        if (cars.isEmpty()) {
            Text(text = "No cars available")
        } else {
            cars.forEach { car ->
                Row(Modifier.clickable {
                    navController.navigate("details/${car.plateNumber}")
                }) {
                    Spacer(Modifier.height(10.dp))
                    Text(text = car.plateNumber)
                }
            }
        }
    }
}
