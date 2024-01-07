package com.example.atmosphere

object WeatherContext {
    public lateinit var location : Place
    public var setLocationCallback = {}

    fun settLocation(location : Place) {
        this.location = location
        this.setLocationCallback()
    }
}