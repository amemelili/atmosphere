package com.example.atmosphere

import com.example.atmosphere.UserData
import retrofit.Call
import retrofit.http.GET

interface ApiService {

    @GET(" ")
    fun fetchData() : Call<UserData>
}