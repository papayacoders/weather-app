package com.example.weathernow.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weathernow.BuildConfig
import com.example.weathernow.data.Entity.WeatherResponse

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}