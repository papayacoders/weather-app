package com.example.weathernow.data.Entity

data class WeatherResponse(
    val name: String,  // Add this line
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(

    val temp: Double,
    val pressure: Double,  // Added this
    val feels_like: Double,
    val humidity: Int
)

data class Weather(
    val id: Int,
    val description: String,
    val icon: String,
    val main: String?
)

data class Wind(
    val speed: Double
)