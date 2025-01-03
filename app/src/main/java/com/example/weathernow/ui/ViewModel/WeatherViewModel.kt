package com.example.weathernow.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernow.data.Entity.WeatherResponse
import com.example.weathernow.data.ResourceState.ResourceState
import com.example.weathernow.data.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepo
) : ViewModel() {
    private val _weatherState = MutableStateFlow<ResourceState<WeatherResponse>>(ResourceState.Loading())
    val weatherState: StateFlow<ResourceState<WeatherResponse>> = _weatherState.asStateFlow()

    private val _cityName = MutableStateFlow("London")
    val cityName: StateFlow<String> = _cityName.asStateFlow()

    init {
        getWeather()
    }

    fun getWeather() {
        viewModelScope.launch {
            repository.getWeather(_cityName.value)
                .collectLatest { result ->
                    _weatherState.value = result
                }
        }
    }

    fun updateCity(newCity: String) {
        _cityName.value = newCity
        getWeather()
    }
}