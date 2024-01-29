package com.example.atmosphere

data class HourlyWeather(
    var latitude : Float,
    var longitude : Float,
    var generationtime_ms : Float,
    var utc_offset_seconds : Int,
    var timezone : String,
    var timezone_abbreviation : String,
    var elevation : Int,
    var hourly_units : HourlyUnits,
    var hourly : Hourly

):java.io.Serializable

data class HourlyUnits(
    var time : String,
    var temperature_2m : String,
    var precipitation : String,
    var weather_code : String
):java.io.Serializable

data class Hourly(
    var time : Array<String>,
    var temperature_2m : Array<Float>,
    var precipitation : Array<Float>,
    var weather_code : Array<Int>
):java.io.Serializable

data class HourlyItem(
    var time : String,
    var temperature_2m : Float,
    var precipitation : Float,
    var weather_code : Int
):java.io.Serializable {
    override fun toString(): String {
        return time + " / " + WeatherStatus.getStatusByWeatherCode(weather_code) + " / " + temperature_2m + " / " + precipitation
    }
}