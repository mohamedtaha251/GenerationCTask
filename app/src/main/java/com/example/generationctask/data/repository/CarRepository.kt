package com.example.generationctask.data.repository

import Car
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Types
import java.io.IOException

class CarRepository(private val context: Context) {

    fun getCars(): List<Car> {
        val json = try {
            context.assets.open("cars.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            return emptyList()
        }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val listType = Types.newParameterizedType(List::class.java, Car::class.java)
        val adapter = moshi.adapter<List<Car>>(listType)

        return adapter.fromJson(json) ?: emptyList()
    }
}
