package com.example.atmosphere

import android.widget.ImageView

object WeatherStatus {

    /*
    *   dictionnaire associant les codes WMO à l'état de la météo correspondant
    */

    private val weatherCodeMap = mapOf(
        0 to "Ciel dégagé",
        1 to "Principalement clair",
        2 to "Partiellement nuageux",
        3 to "Ciel couvert",
        45 to "Brumeux",
        51 to "Légère bruine",
        53 to "Bruine modérée",
        55 to "Bruine dense",
        56 to "Légère bruine verglaçante",
        57 to "Forte bruine verglaçante",
        61 to "Légèrement pluvieux",
        63 to "Pluvieux",
        65 to "Fortement pluvieux",
        66 to "Légère pluie verglaçante",
        67 to "Pluie verglaçante",
        71 to "Légères chutes de neige",
        73 to "Chutes de neige",
        75 to "Fortes chutes de neige",
        77 to "Chutes de neige en grains",
        80 to "Légère averse",
        81 to "Averse",
        82 to "Forte averse",
        85 to "Légère averse de neige",
        86 to "Forte averse de neige",
        95 to "Orageux",
        96 to "Orageux avec chutes de grêle",
        99 to "Orageux avec fortes chutes de grêle"
    )

    private val weatherImageMap = mapOf(
        0 to R.drawable.ic_sunny,
        1 to R.drawable.ic_sunny,
        2 to R.drawable.ic_partly_cloudy,
        3 to R.drawable.ic_cloudy,
        45 to R.drawable.ic_fog,
        51 to R.drawable.ic_rain,
        53 to R.drawable.ic_rain,
        55 to R.drawable.ic_rain,
        56 to R.drawable.ic_freezing_rain,
        57 to R.drawable.ic_freezing_rain,
        61 to R.drawable.ic_rain,
        63 to R.drawable.ic_rain,
        65 to R.drawable.ic_rain,
        66 to R.drawable.ic_freezing_rain,
        67 to R.drawable.ic_freezing_rain,
        71 to R.drawable.ic_snow,
        73 to R.drawable.ic_snow,
        75 to R.drawable.ic_snow,
        77 to R.drawable.ic_snow,
        80 to R.drawable.ic_rain,
        81 to R.drawable.ic_rain,
        82 to R.drawable.ic_rain,
        85 to R.drawable.ic_snow,
        86 to R.drawable.ic_snow,
        95 to R.drawable.ic_storm,
        96 to R.drawable.ic_storm_rain,
        99 to R.drawable.ic_storm_rain

        )

    private val weatherBackgroungMap = mapOf(
        0 to R.drawable.ic_sunny,
        1 to R.drawable.ic_sunny,
        2 to R.drawable.ic_partly_cloudy,
        3 to R.drawable.ic_cloudy,
        45 to R.drawable.ic_fog,
        51 to R.drawable.ic_rain,
        53 to R.drawable.ic_rain,
        55 to R.drawable.ic_rain,
        56 to R.drawable.ic_freezing_rain,
        57 to R.drawable.ic_freezing_rain,
        61 to R.drawable.ic_rain,
        63 to R.drawable.ic_rain,
        65 to R.drawable.ic_rain,
        66 to R.drawable.ic_freezing_rain,
        67 to R.drawable.ic_freezing_rain,
        71 to R.drawable.ic_snow,
        73 to R.drawable.ic_snow,
        75 to R.drawable.ic_snow,
        77 to R.drawable.ic_snow,
        80 to R.drawable.ic_rain,
        81 to R.drawable.ic_rain,
        82 to R.drawable.ic_rain,
        85 to R.drawable.ic_snow,
        86 to R.drawable.ic_snow,
        95 to R.drawable.ic_storm,
        96 to R.drawable.ic_storm_rain,
        99 to R.drawable.ic_storm_rain

    )


    /*
    *   cette méthode retourne une chaîne de caractères sur l'état de la météo
    *   en fonction du code WMO (WMO Weather interpretation code) donné en paramètre
    */
    fun getStatusByWeatherCode(weatherCode : Int) : String {
        return weatherCodeMap.getValue(weatherCode)
    }

    fun getImageStatusByWeatherCode(weatherCode : Int) : Int {
        return weatherImageMap.getValue(weatherCode)
    }

}