package com.example.atmosphere

data class Place(
    var name: String,
    var department: String,
    var latitude: Float,
    var longitude: Float
):java.io.Serializable {
    override fun toString(): String {
        return this.name + " (" + this.department + ")"
    }
}