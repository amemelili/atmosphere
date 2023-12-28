package com.example.atmosphere

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

    /*
    *   cette méthode retourne une chaîne de caractères sur l'état de la météo
    *   en fonction du code WMO (WMO Weather interpretation code) donné en paramètre
    */

    fun getStatusByWeatherCode(weatherCode : Int) : String {
        return weatherCodeMap.getValue(weatherCode)
    }
}