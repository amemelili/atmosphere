package com.example.atmosphere

data class DailyWeather(
    var latitude : Float,
    var longitude : Float,
    var generationtime_ms : Float,
    var utc_offset_seconds : Int,
    var timezone : String,
    var timezone_abbreviation : String,
    var elevation : Int,
    var daily_units : DailyUnits,
    var daily : Daily

):java.io.Serializable

data class DailyUnits(
    var time : String,
    var weather_code : String,
    var temperature_2m_max : String,
    var temperature_2m_min : String,
    var precipitation_probability_max : String
):java.io.Serializable

data class Daily(
    var time : Array<String>,
    var weather_code : Array<Int>,
    var temperature_2m_max : Array<Float>,
    var temperature_2m_min : Array<Float>,
    var precipitation_probability_max : Array<Int>
):java.io.Serializable