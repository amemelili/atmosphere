package com.example.atmosphere

import com.example.atmosphere.UserData
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("api/users/2")
    fun fetchData(): Call<UserData>
}