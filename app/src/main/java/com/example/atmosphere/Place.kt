package com.example.atmosphere

data class Places(
    var places: List<Place>
):java.io.Serializable

data class Place(
    var name: String,
    var department: String,
    var latitude: Float,
    var longitude: Float
):java.io.Serializable