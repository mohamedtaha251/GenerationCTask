package com.example.generationctask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.generationctask.data.repository.CarRepository
import com.example.generationctask.ui.screen.details.CarDetailsScreen
import com.example.generationctask.ui.screen.home.HomeScreen
import com.example.generationctask.ui.theme.GenerationCTaskTheme
import com.example.generationctask.ui.viewmodel.CarViewModel
import com.example.generationctask.ui.viewmodel.CarViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val carRepository = CarRepository(context = this)
            val carViewModel: CarViewModel = viewModel(factory = CarViewModelFactory(carRepository))

            GenerationCTaskTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController = navController, viewModel = carViewModel)
                    }
                    composable("details/{carId}") { backStackEntry ->
                        val carId = backStackEntry.arguments?.getString("carId")?.toInt()
                        CarDetailsScreen(navController = navController, carId = carId, viewModel = carViewModel)
                    }
                }
            }
        }
    }
}