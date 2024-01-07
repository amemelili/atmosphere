package com.example.atmosphere

data class Place(
    var name: String,
    var department: String,
    var latitude: Float,
    var longitude: Float
):java.io.Serializable {
    override fun toString(): String {
        if (department != "") {
            return this.name + " (" + this.department + ")"
        } else {
            return this.name
        }
    }
}