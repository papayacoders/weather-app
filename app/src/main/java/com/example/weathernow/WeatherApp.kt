package com.example.weathernow

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
    companion object{
        private const val TAG="WeatherOne"

    }
}