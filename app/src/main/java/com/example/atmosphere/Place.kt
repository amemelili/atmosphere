package com.example.atmosphere

data class Place(
    var name: String,
    var department: String,
    var latitude: Double,
    var longitude: Double
):java.io.Serializable {
    override fun toString(): String {
        if (department != "") {
            return this.name + " (" + this.department + ")"
        } else {
            return this.name
        }
    }
}