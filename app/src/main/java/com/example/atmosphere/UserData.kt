package com.example.atmosphere

data class UserData(
    var data: User,
    var support: Support
):java.io.Serializable

data class User(
    var id: Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String
):java.io.Serializable

data class Support(
    var url: String,
    var text: String
):java.io.Serializable