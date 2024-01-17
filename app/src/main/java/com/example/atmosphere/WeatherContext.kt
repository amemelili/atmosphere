package com.example.atmosphere

import android.location.Location

object WeatherContext {
    public lateinit var initLocation : Location
    public lateinit var location : Place
    public var updateLocationCallback = {}

    fun updateLocation(location : Place) {
        this.location = location
        this.updateLocationCallback()
    }
}