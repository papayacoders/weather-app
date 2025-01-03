package com.example.weathernow.data.repository

import com.example.weathernow.data.Entity.WeatherResponse
import com.example.weathernow.data.ResourceState.ResourceState
import com.example.weathernow.data.api.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeather(cityName: String): Flow<ResourceState<WeatherResponse>> = flow {
        emit(ResourceState.Loading())
        try {
            val response = weatherApi.getWeather(cityName)
            emit(ResourceState.Success(response))
        } catch (e: Exception) {
            emit(ResourceState.Error(e.message ?: "An error occurred"))
        }
    }
}