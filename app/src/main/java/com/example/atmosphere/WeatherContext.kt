package com.example.atmosphere

object WeatherContext {
    public lateinit var location : Place
    public var updateLocationCallback = {}

    fun updateLocation(location : Place) {
        this.location = location
        this.updateLocationCallback()
    }
}