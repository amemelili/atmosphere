package com.example.atmosphere

data class RealtimeWeather(
    var latitude : Float,
    var longitude : Float,
    var generationtime_ms : Float,
    var utc_offset_seconds : Int,
    var timezone : String,
    var timezone_abbreviation : String,
    var elevation : Int,
    var current_units : RealtimeUnits,
    var current : Realtime

):java.io.Serializable

data class RealtimeUnits(
    var time : String,
    var interval : String,
    var temperature_2m : String,
    var precipitation : String,
    var weather_code : String
):java.io.Serializable

data class Realtime(
    var time : String,
    var interval : Int,
    var temperature_2m : Float,
    var precipitation : Float,
    var weather_code : Int
):java.io.Serializable