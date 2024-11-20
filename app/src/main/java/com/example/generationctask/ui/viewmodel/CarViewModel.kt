package com.example.generationctask.ui.viewmodel

import Car
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.generationctask.data.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarViewModel(private val repository: CarRepository) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    fun loadCars() {
        viewModelScope.launch {
            _cars.value = repository.getCars()
        }
    }

    fun searchCars(price: Double?, color: String?) {
        val filteredCars = repository.getCars().filter { car ->
            (price == null || car.unitPrice == price) &&
            (color.isNullOrEmpty() || car.color.equals(color, true))
        }
        _cars.value = filteredCars
    }

}
